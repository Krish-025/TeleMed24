package com.telemed.appointmententity;

import org.springframework.stereotype.Component;

@Component
public class ViewAppointment {
	private String doctorName;
	private String patientName;
	private String mode;
	private String prescription;
	private String specialization;
	private String date;
	
	@Override
	public String toString() {
		return "ViewAppointment [doctorName=" + doctorName + ", patientName=" + patientName + ", mode=" + mode
				+ ", prescription=" + prescription + ", specialization=" + specialization + ", date=" + date + "]";
	}
	
	public ViewAppointment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ViewAppointment(String doctorName, String patientName, String mode, String prescription,
			String specialization, String date) {
		super();
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.mode = mode;
		this.prescription = prescription;
		this.specialization = specialization;
		this.date = date;
	}
	
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
