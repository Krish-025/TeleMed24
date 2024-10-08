package com.telemed.userentities;

import java.util.ArrayList;
import java.util.List;

import com.telemed.appointmententity.Appointment;

public abstract class User {
	public int id;
	public String name;
	public String email;
	public String phoneNo;
	public String city;
	public String password;
	
	// One User (Doctor/Patient) has many appointments
	List<Appointment> appointments=new ArrayList<>();  

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phoneNo=" + phoneNo + ", city=" + city
				+ ", appointments=" + appointments + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Appointment appointment) {
		this.appointments.add(appointment);
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String email, String phoneNo, String city, Appointment appointment) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.city = city;
		this.appointments.add(appointment);
	}
}
