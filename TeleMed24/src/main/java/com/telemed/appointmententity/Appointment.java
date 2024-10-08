package com.telemed.appointmententity;

import org.springframework.stereotype.Component;

@Component
public class Appointment {
	private int id;
	private int patientId;
	private int doctorId;
	private String date;
	private String prescription;
	private String mode;
	
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointment(int id, int patientId, int doctorId, String date, String prescription, String mode) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.prescription = prescription;
		this.mode = mode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	
	
}
