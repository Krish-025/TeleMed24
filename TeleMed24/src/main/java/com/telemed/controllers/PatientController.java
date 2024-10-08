package com.telemed.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telemed.appointmententity.Appointment;
import com.telemed.appointmententity.ViewAppointment;
import com.telemed.dao.AppointmentDaoImpl;
import com.telemed.dao.DoctorDaoImpl;
import com.telemed.dao.PatientDaoImpl;
import com.telemed.emailservices.EmailServiceImpl;
import com.telemed.exceptions.UserWithEmailAlreadyExistException;
import com.telemed.userentities.Doctor;
import com.telemed.userentities.Patient;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientDaoImpl patientDao;
	
	@Autowired 
	private DoctorDaoImpl doctorDao;
	
	@Autowired
	private EmailServiceImpl mailService;
	
	@Autowired
	private AppointmentDaoImpl appointmentDao;
	
	// View Profile
	@GetMapping("/view-profile")
	public ResponseEntity<Patient> viewProfile(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		
		if(session==null) {
			System.out.println("\nPatient is not logged-in Session= "+session);
			return new ResponseEntity<>(null,HttpStatus.OK);
		} else	System.out.println("Patient session already existed");
		
		String email=(String) session.getAttribute("USER_EMAIL");
		Patient patient=patientDao.extract(email);
		System.out.println("Viewing patient profile: "+email);
		System.out.println("View Profile: " + patient);
		return new ResponseEntity<>(patient,HttpStatus.OK);
	}
	
	// Logout API
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		System.out.println("\nPatient logout");
		HttpSession session = request.getSession(false); // Get the current session without creating a new one
		if (session != null) {
			session.invalidate(); // Invalidate the user's session
		}
		return new ResponseEntity<String>("user logout",HttpStatus.OK);
	}
	
	@PostMapping("/reqOTP")
	public ResponseEntity<String> requestOtp(@RequestBody Map<String,String> requestBody)
	{
		String to=requestBody.get("email");
		System.out.println("Sending otp to "+to);
		String otp=mailService.sendOtp(to);
		System.out.println("otp = "+otp);  // working 
		
		return new ResponseEntity<String>(otp,HttpStatus.OK);
	}
	
	@PostMapping("/emailexist")
	public ResponseEntity<String> checkEmailExists(@RequestBody Map<String,String> request) {
		System.out.println("Checking patient exist in DB : "+request.get("email"));
		patientDao.extract(request.get("email"));
		System.out.println("dfghdfgdfg");
		throw new UserWithEmailAlreadyExistException();
	}
	
	@PostMapping ("/register")
	public ResponseEntity<String> register(@RequestBody Patient patient) {
		System.out.println("Storing patient into db");
		System.out.println(patient);
		
		//One exception may be patient already present with given email
		patientDao.store(patient);
		
		return new ResponseEntity<>("sign-up successfull",HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Map<String,String> request,HttpServletRequest requestSession) throws ParseException  {
		System.err.println("\n in patient update");
		String name=(String) request.get("name");
		String phoneNo=(String) request.get("phoneNo");
		String city=(String) request.get("city");
		Date dob=new SimpleDateFormat("yyyy-mm-dd").parse(request.get("dob"));
		String email=(String) requestSession.getSession().getAttribute("USER_EMAIL");
		
		Patient patient=patientDao.extract(email);
		patient.setName(name);
		patient.setPhoneNo(phoneNo);
		patient.setDob(dob);
		patient.setCity(city);
		patientDao.update(patient);
		return new ResponseEntity<String>("patient update",HttpStatus.OK);
	}
	
	
	// To extract all appointments of patient
	@GetMapping("/getappointments")
	public ResponseEntity<List<ViewAppointment>> getAppointments(HttpServletRequest request) {
		List<Appointment> appointments=null;
		List<ViewAppointment> viewAppointments=new ArrayList<>();
		
		HttpSession session=request.getSession(false);
		
		if(session==null) {
			System.out.println("\nPatient is not logged-in Session= "+session);
			return new ResponseEntity<>(null,HttpStatus.OK);
		} else	System.out.println("Patient session already existed");
		String patientEmail=(String) session.getAttribute("USER_EMAIL");
		int patientKey=patientDao.extract(patientEmail).getId();
		
		appointments=appointmentDao.extractPatientAppointments(patientKey);
		for(Appointment appointment:appointments) {
			Doctor doctor=doctorDao.extract(appointment.getDoctorId());
			Patient patient=patientDao.extract(appointment.getPatientId());
			
			ViewAppointment viewAppointment=new ViewAppointment(doctor.getName(),
																patient.getName(),
																appointment.getMode(),
																appointment.getPrescription(),
																doctor.getSpecialization(),
																appointment.getDate());
			viewAppointments.add(viewAppointment);
		}
		
		return new ResponseEntity<List<ViewAppointment>>(viewAppointments,HttpStatus.OK);
	}
}
