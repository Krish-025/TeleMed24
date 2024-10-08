package com.telemed.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.telemed.appointmententity.Appointment;
import com.telemed.dao.interfaces.AppointmentDao;
import com.telemed.dao.rowmapper.AppointmentRowMapper;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	private AppointmentRowMapper appointmentRowMapper=AppointmentRowMapper.getRowMapper();

	@Override
	public List<Appointment> extractAll(int id) {
		String extractAppointmentQuery="SELECT * FROM appointment WHERE patient_id=?";
		
		List<Appointment> appointments=jdbcTemplate.query(extractAppointmentQuery, appointmentRowMapper, id);
		return appointments;
	}

	@Override
	public void store(Appointment appointment) {
		String storeAppointmentQuery="""
					INSERT INTO appointment (patient_id,doctor_id,appointment_date,prescription,appointment_mode)
					VALUES (?,?,?,?,?)
				""";
		jdbcTemplate.update(storeAppointmentQuery,
							appointment.getPatientId(),
							appointment.getDoctorId(),
							appointment.getDate(),
							appointment.getPrescription(),
							appointment.getMode());
	}

	@Override
	public List<Appointment> extractPatientAppointments(int patientKey) {
		String extractPatientQuery="""
					SELECT * FROM appointment WHERE patient_id=?
				""";
		List<Appointment> appointments=jdbcTemplate.query(extractPatientQuery,appointmentRowMapper,patientKey);
		return appointments;
	}

	@Override
	public List<Appointment> extreactDoctorAppointment(int doctorKey) {
		String extractPatientQuery="""
				SELECT * FROM appointment WHERE doctor_id=?
			""";
		List<Appointment> appointments=jdbcTemplate.query(extractPatientQuery,appointmentRowMapper,doctorKey);
		return appointments;
	}

	@Override
	public List<Appointment> extractDoctorCurrentAppointments(int doctorKey) {
		String extractCurrentAppointment="""
					SELECT * FROM appointment * appointment_slot 
					WHERE appointment.doctor_id=? AND
					appointment_slot.end > CURTIME()
				""";
		List<Appointment> appointments=jdbcTemplate.query(extractCurrentAppointment,appointmentRowMapper,doctorKey);
		return appointments;
	}
	
	
		
}
