package com.telemed.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.telemed.dao.interfaces.DoctorDao;
import com.telemed.dao.rowmapper.DoctorRowMapper;
import com.telemed.exceptions.UserNotFoundException;
import com.telemed.exceptions.UserWithEmailAlreadyExistException;
import com.telemed.userentities.Doctor;


@Repository
public class DoctorDaoImpl implements DoctorDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private DoctorRowMapper doctorRowMapper=DoctorRowMapper.getRowMapper();
	
	// Method to store doctor into DB
	@Override
	public int store(Doctor doctor) {
		
		String storeDoctorQuery="""
				INSERT INTO doctor
				(name,email,phone_no,city,address,certificate_no,rating,mode_of_consultation,specialization,password)
				VALUES (?,?,?,?,?,?,?,?,?,?)
				"""; 
		int rowsAffected=0;
		
		try {
			rowsAffected=jdbcTemplate.update(storeDoctorQuery,
											doctor.getName(),
											doctor.getEmail(),
											doctor.getPhoneNo(),
											doctor.getCity(),
											doctor.getAddress(),
											doctor.getCertificateNo(),
											doctor.getRating(),
											doctor.getModeOfConsultation(),
											doctor.getSpecialization(),
											doctor.getPassword());
		} catch (DataIntegrityViolationException e) {
			throw new UserWithEmailAlreadyExistException();
		}
		return rowsAffected;
	}
	
	// Method to update doctor
	@Override
	public void update(Doctor doctor) {
		String updatePatientQuery="""
				UPDATE doctor
				SET name=?,
				city=?,
				phone_no=?,
				address=? WHERE email=?
 				""";
		jdbcTemplate.update(updatePatientQuery,doctor.getName(),
											   doctor.getCity(),
											   doctor.getPhoneNo(),
											   doctor.getAddress(),
											   doctor.getEmail());
	}
	
	
	// Method to extract all doctors
	@Override
	public List<Doctor> extractAll() {
		String extractAllDoctorQuery="SELECT * FROM doctor";
		List<Doctor> doctors=null;
		try {
			doctors=jdbcTemplate.query(extractAllDoctorQuery, doctorRowMapper);
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException("empty set");
		}
		return doctors;
	}
	
	// Method to extract doctor by email
	@Override
	public Doctor extract(String email) {
		String extractPatientEmailQuery="SELECT * FROM doctor WHERE email=?";
		Doctor doctor=null;
		try {
			doctor=jdbcTemplate.queryForObject(extractPatientEmailQuery, doctorRowMapper, email);
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException(email);
		}
		return doctor;
	}
	
	
	// Method to extract whole Doctor Object from DB  
	@Override
	public Doctor extract(int primaryKey) {
		String extractDoctorQuery="SELECT * FROM doctor WHERE id=?";
		Doctor doctor=jdbcTemplate.queryForObject(extractDoctorQuery, doctorRowMapper, primaryKey);
		return doctor;
	}
		
	
	// Method to extract whole Doctor by name Object from DB
	@Override
	public List<Doctor> extractByName(String name) {
		String extractDoctorQueryByName="""
				SELECT * FROM doctor WHERE name LIKE ? AND
				(mode_of_consultation='OFFLINE' OR mode_of_consultation='BOTH')
				""";
		List<Doctor> doctorList=jdbcTemplate.query(extractDoctorQueryByName, doctorRowMapper, name);
		return doctorList;
	}

	
	// Method to extract whole Doctor by city Object from DB  
	@Override
	public List<Doctor> extractByCity(String city) {	
		String extractDoctorQueryByName="""
				SELECT * FROM doctor WHERE city=? AND 
				(mode_of_consultation='OFFLINE' OR mode_of_consultation='BOTH')
				""";
		List<Doctor> doctorList=jdbcTemplate.query(extractDoctorQueryByName, doctorRowMapper, city);
		return doctorList;
	}
	
	
	// Method to extract whole Doctor by specialization Object from DB  
	@Override
	public List<Doctor> extractBySpecialization(String specialization) {
		String extractDoctorQueryBySpecialization="""
				SELECT * FROM doctor WHERE specialization=? 
				AND (mode_of_consultation='OFFLINE' OR mode_of_consultation='BOTH')	
				""";
				
		List<Doctor> doctorList=jdbcTemplate.query(extractDoctorQueryBySpecialization, doctorRowMapper, specialization);
		return doctorList;
	}
	
	
	
	
	
	
	
	// Methods to extract individual attributes of Doctor
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
	public String extractCertificateNo(int primaryKey) {
		String certificateNo=null;
		String extractcertificateNoQuery="SELECT certificate_no FROM patient WHERE id=?";
		certificateNo=(String)jdbcTemplate.queryForObject(extractcertificateNoQuery, 
										 String.class, 
										 primaryKey);
		return certificateNo;
	}


	@Override
	public int extractRating(int primaryKey) {
		Integer rating=null;
		String extractRatingQuery="SELECT rating FROM patient WHERE id=?";
		rating=(Integer)jdbcTemplate.queryForObject(extractRatingQuery,
										Integer.class,
										primaryKey);
		return rating;
	}

	@Override
	public String extractSpecialization(int primaryKey) {
		String specialization=null;
		String extractSpecializationQuery="SELECT specialization FROM patient WHERE id=?";
		specialization=(String)jdbcTemplate.queryForObject(extractSpecializationQuery, 
										 String.class, 
										 primaryKey);
		return specialization;
	}

	@Override
	public List<Doctor> extractOnineDoctor(String specialization) {
		String extractOnlineDoctoQuery="""
				SELECT * FROM doctor where mode_of_consultation=?
			""";
		List<Doctor> doctors=jdbcTemplate.query(extractOnlineDoctoQuery,doctorRowMapper,specialization);
		return doctors;
	}
	


}
