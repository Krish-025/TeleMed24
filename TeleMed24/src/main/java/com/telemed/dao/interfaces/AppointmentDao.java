package com.telemed.dao.interfaces;

import java.util.List;

import com.telemed.appointmententity.Appointment;


public interface AppointmentDao {
	public void store(Appointment appointment);
	public List<Appointment> extractPatientAppointments(int patientKey);
	public List<Appointment> extreactDoctorAppointment(int doctorKey);
	public List<Appointment> extractDoctorCurrentAppointments(int doctorKey);
	public List<Appointment> extractAll(int id);
}
