package com.telemed.appointmententity;

import java.sql.Time;

import org.springframework.stereotype.Component;

@Component
public class AppointmentTimeSlot {
	private int id;
	private int doctorId;
	private Time startTime;
	private Time endTime;
	private int currentNoOfPatient;
	private int maxPatientPerSlot;
	
	public AppointmentTimeSlot(int id, int doctorId, Time startTime, Time endTime, int currentNoOfPatient,
			int maxPatientPerSlot) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.currentNoOfPatient = currentNoOfPatient;
		this.maxPatientPerSlot = maxPatientPerSlot;
	}
	
	public AppointmentTimeSlot() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public int getCurrentNoOfPatient() {
		return currentNoOfPatient;
	}
	public void setCurrentNoOfPatient(int currentNoOfPatient) {
		this.currentNoOfPatient = currentNoOfPatient;
	}
	public int getMaxPatientPerSlot() {
		return maxPatientPerSlot;
	}
	public void setMaxPatientPerSlot(int maxPatientPerSlot) {
		this.maxPatientPerSlot = maxPatientPerSlot;
	}
	
	
}
