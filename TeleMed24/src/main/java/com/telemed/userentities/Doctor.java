package com.telemed.userentities;

import org.springframework.stereotype.Component;

import com.telemed.appointmententity.Appointment;

@Component
public class Doctor extends User{
	private String certificateNo;
	private int rating;
	private String specialization;
	private String address;
	private String modeOfConsultation;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getModeOfConsultation() {
		return modeOfConsultation;
	}
	public void setModeOfConsultation(String modeOfConsultation) {
		this.modeOfConsultation = modeOfConsultation;
	}	
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	

	
	@Override
	public String toString() {
		return "Doctor [certificateNo=" + certificateNo + ", rating=" + rating + ", specialization=" + specialization
				+ ", address=" + address + ", modeOfConsultation=" + modeOfConsultation + ", id=" + id + ", name="
				+ name + ", email=" + email + ", phoneNo=" + phoneNo + ", city=" + city + ", password=" + password
				+ ", appointments=" + appointments + "]";
	}
	public Doctor(String certificateNo, int rating, String specialization) {
		super();
		this.certificateNo = certificateNo;
		this.rating = rating;
		this.specialization = specialization;
	}
	public Doctor() {
		super();
		this.modeOfConsultation="BOTH";
	}
	public Doctor(int id, String name, String email, String phoneNo, String city, Appointment appointment) {
		super(id, name, email, phoneNo, city, appointment);
	}
	
	
}
