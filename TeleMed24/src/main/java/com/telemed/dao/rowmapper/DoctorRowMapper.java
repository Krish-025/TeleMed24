package com.telemed.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.telemed.userentities.Doctor;


// We will make it singleton class
public class DoctorRowMapper implements RowMapper<Doctor>{
	
	private static DoctorRowMapper doctorRowMapper;
	
	@Override
	public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Doctor doctor=new Doctor();
		
		doctor.setId(rs.getInt("id"));
		doctor.setName(rs.getString("name"));
		doctor.setEmail(rs.getString("email"));
		doctor.setPhoneNo(rs.getString("phone_no"));
		doctor.setCity(rs.getString("city"));
		doctor.setAddress(rs.getString("address"));
		doctor.setModeOfConsultation(rs.getString("mode_of_consultation"));
		doctor.setCertificateNo(rs.getString("certificate_no"));
		doctor.setRating(rs.getInt("rating"));
		doctor.setSpecialization(rs.getString("specialization"));
		doctor.setPassword(rs.getString("password"));
		
		return doctor;
	}
	
	// this constructor make it singleton
	private DoctorRowMapper() {
		super();
	}

	public static DoctorRowMapper getRowMapper() {
		if(doctorRowMapper==null) {
			doctorRowMapper=new DoctorRowMapper();
		}
		return doctorRowMapper;
	}
}
