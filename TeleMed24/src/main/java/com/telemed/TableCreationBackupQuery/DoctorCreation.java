package com.telemed.TableCreationBackupQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DoctorCreation {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String doctorCreationQuery=
			"CREATE TABLE doctor (id int AUTO_INCREMENT, "
							+ "name VARCHAR(255) NOT NULL, "
							+ "email VARCHAR(255) NOT NULL, "
							+ "phone_no VARCHAR(255), "
							+ "city VARCHAR(255) NOT NULL, "
							+ "certificate_no VARCHAR(255) NOT NULL, "
							+ "rating int, "
							+ "specialization VARCHAR(255) NOT NULL, "
							+ "password VARCHAR(255) NOT NULL, "
							+ "PRIMARY KEY(id))";

	private String alterQuery=" ALTER TABLE doctor AUTO_INCREMENT=100 ";
	
	public DoctorCreation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void createDoctorTable() {
		jdbcTemplate.execute(doctorCreationQuery);
		jdbcTemplate.execute(alterQuery);
	}
}
