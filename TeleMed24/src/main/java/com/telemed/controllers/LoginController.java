package com.telemed.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telemed.dao.DoctorDaoImpl;
import com.telemed.dao.PatientDaoImpl;
import com.telemed.userentities.Doctor;
import com.telemed.userentities.Patient;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {
	
	@Autowired
	private PatientDaoImpl patientDao;
	
	@Autowired
	private DoctorDaoImpl doctorDao;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String,String> requestBody,
										HttpServletRequest request,
										HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		if(session.isNew()) {
			System.out.println("\nUser New Session");
		} else {
			System.out.println("\nUser session already existed");
		}
		
		System.out.println(requestBody.get("user")+" to login");
		System.out.println("login detail: email = "+requestBody.get("email")+
				"\n               password = "+requestBody.get("password")+"\n");
		
		String responseString="login successful";
		
		if(requestBody.get("user").equals("PATIENT")) {
			Patient patient=patientDao.extract(requestBody.get("email"));
			if(!requestBody.get("password").equals(patient.getPassword()))
				responseString="Incorrect password";
		} else {
			Doctor doctor=doctorDao.extract(requestBody.get("email"));
			if(!requestBody.get("password").equals(doctor.getPassword())) 
				responseString="Incorrect password";
		}

		session.setAttribute("USER_EMAIL",requestBody.get("email"));
		session.setAttribute("USER_MODE", requestBody.get("user"));
		
		Cookie cookie=new Cookie("USER_MODE", requestBody.get("user"));
		response.addCookie(cookie);
		
		System.out.println(responseString);
		return new ResponseEntity<>(responseString,HttpStatus.OK);
	}
}
