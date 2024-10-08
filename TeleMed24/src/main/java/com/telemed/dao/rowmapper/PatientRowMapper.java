package com.telemed.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.telemed.userentities.Patient;

public class PatientRowMapper implements RowMapper<Patient> {
	
	private static PatientRowMapper patientRowMapper;

	@Override
	public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
		Patient patient=new Patient();
		
		patient.setId(rs.getInt("id"));
		patient.setName(rs.getString("name"));
		patient.setEmail(rs.getString("email"));
		patient.setPassword(rs.getString("phone_no"));
		patient.setPhoneNo(rs.getString("phone_no"));
		patient.setDob(rs.getDate("dob"));
		patient.setCity(rs.getString("city"));
		patient.setPassword(rs.getString("password"));
		
		return patient;
	}
	
	// this constructor make if singleton
	private PatientRowMapper() {
		super();
	}
	
	public static PatientRowMapper getRowMapper() {
		if(patientRowMapper==null) {
			patientRowMapper=new PatientRowMapper();
		}
		return patientRowMapper;
	}
	
}
