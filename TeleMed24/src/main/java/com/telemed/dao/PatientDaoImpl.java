package com.telemed.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.telemed.dao.interfaces.PatientDao;
import com.telemed.dao.rowmapper.PatientRowMapper;
import com.telemed.exceptions.UserNotFoundException;
import com.telemed.exceptions.UserWithEmailAlreadyExistException;
import com.telemed.userentities.Patient;


@Repository
public class PatientDaoImpl implements PatientDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private PatientRowMapper patientRowMapper=PatientRowMapper.getRowMapper();
	
	// Method to store patient into DB
	@Override
	public int store(Patient patient){
		
		String storePatientQuery="""
				INSERT INTO patient 
				(name,email,phone_no,dob,city,password)
				VALUES (?,?,?,?,?,?)
				""";
				
		int rowsAffected=0;
		try {
			rowsAffected=jdbcTemplate.update(storePatientQuery,
					patient.getName(),
					patient.getEmail(),
					patient.getPhoneNo(),
					patient.getDob(),
					patient.getCity(),
					patient.getPassword()
					);			
		} catch (DataIntegrityViolationException e) {
			throw new UserWithEmailAlreadyExistException();
		}
		System.out.println(rowsAffected);
		return rowsAffected;
	}
	
	// Method to update patient
	@Override
	public void update(Patient patient) {
		String updatePatientQuery="""
				UPDATE patient
				SET name=?,
				city=?,
				phone_no=?,
				dob=? WHERE email=?
 				""";
		jdbcTemplate.update(updatePatientQuery,patient.getName(),
											   patient.getCity(),
											   patient.getPhoneNo(),
											   patient.getDob(),
											   patient.getEmail());
	}
	
	// Method to extract all patients
	@Override
	public List<Patient> extractAll() {
		String extractAllPatientsQuery="SELECT * FROM patient";
		
		List<Patient> patients=jdbcTemplate.query(extractAllPatientsQuery,patientRowMapper);
		return patients;
	}
	
	// Method to extract whole Patient Object from DB  
	@Override
	public Patient extract(int primaryKey) {
		
		String extractPatientQuery="SELECT * FROM patient WHERE id=?";
		
		Patient patient=jdbcTemplate.queryForObject(extractPatientQuery, patientRowMapper, primaryKey);
		
		return patient;
	}
	
	
	// Method to extract whole patient by name Object from DB  
	@Override
	public List<Patient> extractByName(String name) {
		
		String storePatientQuery="SELECT * FROM patient WHERE name=?";
		
		List<Patient> patientList=jdbcTemplate.query(storePatientQuery, patientRowMapper, name);
		return patientList;
	}
	
	
	
	// Method to extract whole Patient by city Object from DB 
	@Override
	public List<Patient> extractByCity(String city) {
		
		String extractPatientQueryByName="SELECT * FROM patient WHERE name=?";
		
		List<Patient> doctorList=jdbcTemplate.query(extractPatientQueryByName,patientRowMapper, city);
		return doctorList;
	}
	
	
	@Override
	public Patient extract(String email) {
		String extractPatientEmailQuery="SELECT * FROM patient WHERE email=?";
		
		Patient patient=null;
		try {
			patient=jdbcTemplate.queryForObject(extractPatientEmailQuery, patientRowMapper, email);
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException(email);
		}
		return patient;
	}
	
	
	
	
	
	
	
	
	@Override
	public String extractName(int primaryKey) {
		String name=null;
		String extractNameQuery="SELECT name FROM patient WHERE id=?";
		name=(String)jdbcTemplate.queryForObject(extractNameQuery, 
										 String.class, 
										 primaryKey);
		return name;
	}

	@Override
	public String extractEmail(int primaryKey) {
		String email=null;
		String extractEmailQuery="SELECT email FROM patient WHERE id=?";
		email=(String)jdbcTemplate.queryForObject(extractEmailQuery, 
										 String.class, 
										 primaryKey);
		return email;
	}

	@Override
	public String extractPhoneNo(int primaryKey) {
		String phoneNo=null;
		String extractPhnoeNoQuery="SELECT phone_no FROM patient WHERE id=?";
		phoneNo=(String)jdbcTemplate.queryForObject(extractPhnoeNoQuery, 
										 String.class, 
										 primaryKey);
		return phoneNo;
	}

	@Override
	public String extractCity(int primaryKey) {
		String city=null;
		String extractCityQuery="SELECT city FROM patient WHERE id=?";
		city=(String)jdbcTemplate.queryForObject(extractCityQuery, 
										 String.class, 
										 primaryKey);
		return city;
	}

	@Override
	public String extractPassword(int primaryKey) {
		String password=null;
		String extractPasswordQuery="SELECT password FROM patient WHERE id=?";
		password=(String)jdbcTemplate.queryForObject(extractPasswordQuery, 
										 String.class, 
										 primaryKey);
		return password;
	}

	@Override
	public Date extractDob(int primaryKey) {
		Date dobDate=null;
		String extractDateQuery="SELECT dob FROM patient WHERE id=?";
		dobDate=(Date)jdbcTemplate.queryForObject(extractDateQuery, 
										 Date.class, 
										 primaryKey);
		return dobDate;
	}

}
