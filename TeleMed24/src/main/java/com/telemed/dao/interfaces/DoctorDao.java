package com.telemed.dao.interfaces;

import java.util.List;

import com.telemed.userentities.Doctor;

public interface DoctorDao extends UserDao{
	int store(Doctor doctor);
	List<Doctor> extractAll();
	void update(Doctor doctor);
	Doctor extract(int primaryKey);
	List<Doctor> extractByName(String name);
	List<Doctor> extractByCity(String city);
	List<Doctor> extractBySpecialization(String specialization);

	
	public Doctor extract(String email);
	public String extractCertificateNo(int primaryKey);
	public String extractSpecialization(int primaryKey);
	public int extractRating(int primaryKey);
	List<Doctor> extractOnineDoctor(String specialization);
}
