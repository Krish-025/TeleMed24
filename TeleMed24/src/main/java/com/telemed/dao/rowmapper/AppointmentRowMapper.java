package com.telemed.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.telemed.appointmententity.Appointment;

public class AppointmentRowMapper implements RowMapper<Appointment>{
	
	static private AppointmentRowMapper appointmentRowMapper;

	@Override
	public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Appointment appointment=new Appointment();
		
		appointment.setId(rs.getInt("id"));
		appointment.setPatientId(rs.getInt("patient_id"));
		appointment.setDoctorId(rs.getInt("doctor_id"));
		appointment.setDate(rs.getString("appointment_date"));
		appointment.setPrescription(rs.getString("prescription"));
		appointment.setMode(rs.getString("appointment_mode"));
		return appointment;
	}
	
	// this constructor make if singleton
	private AppointmentRowMapper() {
		super();
	}
	
	public static AppointmentRowMapper getRowMapper() {
		if(appointmentRowMapper==null) {
			appointmentRowMapper=new AppointmentRowMapper();
		}
		return appointmentRowMapper;
	}
	
}
