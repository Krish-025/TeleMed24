package com.telemed.TableCreationBackupQuery;

import org.springframework.jdbc.core.JdbcTemplate;

public class AppointmentCreation {
	
	JdbcTemplate jdbcTemplate;
	
	private String appointmentCreationQuery=
			"CREATE TABLE appointment (id int AUTO_INCREMENT, "
							+ "patient_id int, "
							+ "doctor_id int, "
							+ "appointment_date Date, "
							+ "medicines text, "
							+ "advise text, "
							+ "PRIMARY KEY(id),"
							+ "FOREIGN KEY(patient_id) REFERENCES patient(id),"
							+ "FOREIGN KEY(doctor_id) REFERENCES doctor(id) )";

	
	public void createAppointmentTable() {
		jdbcTemplate.execute(appointmentCreationQuery);
	}

}
