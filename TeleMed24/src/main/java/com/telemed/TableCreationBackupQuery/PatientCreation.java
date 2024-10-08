package com.telemed.TableCreationBackupQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class PatientCreation {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String doctorCreationQuery=
			"CREATE TABLE patient (id int AUTO_INCREMENT, "
							+ "name VARCHAR(255) NOT NULL, "
							+ "email VARCHAR(255) NOT NULL, "
							+ "phone_no VARCHAR(255), "
							+ "city VARCHAR(255) NOT NULL, "
							+ "dob Date,"
							+ "password VARCHAR(255) NOT NULL "
							+ "PRIMARY KEY(id) )";

	private String alterQuery=" ALTER TABLE patient AUTO_INCREMENT=1000 ";
	
	public PatientCreation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void createPatientTable() {
		jdbcTemplate.execute(doctorCreationQuery);
		jdbcTemplate.execute(alterQuery);
	}
}
