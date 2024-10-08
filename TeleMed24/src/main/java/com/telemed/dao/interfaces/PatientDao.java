package com.telemed.dao.interfaces;

import java.sql.Date;
import java.util.List;

import com.telemed.userentities.Patient;

public interface PatientDao extends UserDao{
	public int store(Patient patient);
	void update(Patient patient);
	public List<Patient> extractAll();  
	public Patient extract(int primaryKey);  
	public List<Patient> extractByName(String name);  
	public List<Patient> extractByCity(String city);
	public Patient extract(String email);	
	
	// Optional methods
	public String extractName(int primaryKey);
	public String extractEmail(int primaryKey);
	public String extractPhoneNo(int primaryKey);
	public String extractCity(int primaryKey);
	public String extractPassword(int primaryKey);
	public Date extractDob(int primaryKey);
}
