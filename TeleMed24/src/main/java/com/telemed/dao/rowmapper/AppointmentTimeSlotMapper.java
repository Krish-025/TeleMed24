package com.telemed.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.telemed.appointmententity.AppointmentTimeSlot;

public class AppointmentTimeSlotMapper implements RowMapper<AppointmentTimeSlot> {
	
	private static AppointmentTimeSlotMapper appointmentTimeSlotMapper;

	@Override
	public AppointmentTimeSlot mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppointmentTimeSlot appointmentTimeSlot=new AppointmentTimeSlot();
		appointmentTimeSlot.setId(rs.getInt("id"));
		appointmentTimeSlot.setDoctorId(rs.getInt("doctor_id"));
		appointmentTimeSlot.setStartTime(rs.getTime("start"));
		appointmentTimeSlot.setEndTime(rs.getTime("end"));
		appointmentTimeSlot.setMaxPatientPerSlot(rs.getInt("total_patient"));
		appointmentTimeSlot.setCurrentNoOfPatient(rs.getInt("current_patient"));
		
		return appointmentTimeSlot;
	}
	
	public AppointmentTimeSlotMapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static AppointmentTimeSlotMapper getRowMapper() {
		if(appointmentTimeSlotMapper==null)
			appointmentTimeSlotMapper=new AppointmentTimeSlotMapper();
		return appointmentTimeSlotMapper;
	}

}
