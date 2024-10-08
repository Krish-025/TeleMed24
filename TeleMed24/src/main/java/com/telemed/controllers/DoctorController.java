package com.telemed.controllers;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telemed.appointmententity.Appointment;
import com.telemed.appointmententity.AppointmentTimeSlot;
import com.telemed.dao.AppointmentDaoImpl;
import com.telemed.dao.AppointmentTimeSlotDaoImpl;
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
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorDaoImpl doctorDao;	
	
	@Autowired
	private PatientDaoImpl patientDao;
	
	@Autowired
	private AppointmentDaoImpl appointmentDao;
	
	@Autowired
	private EmailServiceImpl mailService;
	
	@Autowired
	private AppointmentTimeSlotDaoImpl timeSlotDao;
	

	@GetMapping("/view-profile")
	public ResponseEntity<Doctor> viewProfile(HttpServletRequest request) {
		HttpSession session=request.getSession(false);
		
		if(session==null) {
			System.out.println("\nPatient is not logged-in Session= "+session);
			return new ResponseEntity<>(null,HttpStatus.OK);
		} else	System.out.println("Patient session already existed");
		
		String email=(String) session.getAttribute("USER_EMAIL");
		Doctor doctor=doctorDao.extract(email);
		System.out.println("Viewing doctor profile: "+email);
		System.out.println("View Profile: " + doctor);
		return new ResponseEntity<>(doctor,HttpStatus.OK);
	}	
	
	@GetMapping("/getalldoctors")
	public ResponseEntity<List<Doctor>> getAllDoctors(HttpServletRequest request) {
		List<Doctor> doctors=doctorDao.extractAll();
		return new ResponseEntity<List<Doctor>>(doctors,HttpStatus.OK);
	}
	
	
	@GetMapping("/getdoctorby")
	public ResponseEntity<List<Doctor>> getDoctorBy(@RequestParam("searchBy") String searchBy,@RequestParam("value") String value) {
		List<Doctor> doctors=null;
		System.out.println("\nSearching doctor by "+searchBy+"\n value = "+value);
		
		if(searchBy.equals("name")) {
			doctors=doctorDao.extractByName(value+"%");
		} else if(searchBy.equals("city")) {
			doctors=doctorDao.extractByCity(value);
		} else {
			doctors=doctorDao.extractBySpecialization(value);
		}
		
		System.out.println(doctors);
		return new ResponseEntity<List<Doctor>>(doctors,HttpStatus.OK);
	}
	
	// Get all available Time slots of a particular doctor
	@GetMapping("/getavailableslots")
	public ResponseEntity<List<AppointmentTimeSlot>> getAvailableSlots(@RequestParam("doctorId") int doctorId) {
		List<AppointmentTimeSlot> slots=null;
		System.out.println("\nSearching available slots of doctor :"+doctorId);
		
		slots=timeSlotDao.extractAvailable(doctorId);
		return new ResponseEntity<List<AppointmentTimeSlot>>(slots,HttpStatus.OK);
	}
	
	// Booking slot
	@PutMapping("/bookslot")
	public ResponseEntity<String> bookSlot(@RequestParam("doctorId") String doctorIdString,
            							   @RequestParam("slotId") String slotIdString,
										   HttpServletRequest request) {
		int doctorId=Integer.parseInt(doctorIdString);
		int slotId=Integer.parseInt(slotIdString);
		
		System.out.println("Booking slot of doctor:"+doctorId);
		
		HttpSession session=request.getSession(false);
		
		String patientEmail=(String) session.getAttribute("USER_EMAIL");
		
		System.out.println("Indide slot book");
		
		AppointmentTimeSlot slot=timeSlotDao.extractById(slotId);
		Doctor doctor=doctorDao.extract(doctorId);
		Patient patient=patientDao.extract(patientEmail);
		
		String doctorName=doctor.getName();
		String patientName=patient.getName();
		String clinicAddress=doctor.getAddress();
		String appointmentTime=slot.getStartTime().toString();
		
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String appointmentDate = currentDate.format(formatter);
        
        Appointment appointment=new Appointment();
        appointment.setPatientId(patient.getId());
        appointment.setDoctorId(doctorId);
        appointment.setDate(appointmentDate);
        appointment.setMode("OFFLINE");
        appointmentDao.store(appointment);
        
        patient.setAppointments(appointment);
        doctor.setAppointments(appointment);
		
		timeSlotDao.updateSlot(slotId);
		mailService.sendAppointmentConfirmationMail(patientEmail,patientName,doctorName,
													clinicAddress,appointmentDate,appointmentTime);
		return new ResponseEntity<String>("slot booked",HttpStatus.OK);
	}
	
	@PutMapping("/update") 
	public ResponseEntity<String> update(@RequestBody Map<String,String> request,HttpServletRequest requestSession) throws ParseException  {
		System.err.println("\n in patient update");
		String name=request.get("name");
		String phoneNo=request.get("phoneNo");
		String city=request.get("city");
		String address=request.get("address");
		String email=(String) requestSession.getSession().getAttribute("USER_EMAIL");
		
		Doctor doctor=doctorDao.extract(email);
		doctor.setName(name);
		doctor.setPhoneNo(phoneNo);
		doctor.setAddress(address);
		doctor.setCity(city);
		doctorDao.update(doctor);
		return new ResponseEntity<String>("patient update",HttpStatus.OK);
	}
	
	@GetMapping("/getappointments")
	public ResponseEntity<List<Appointment>> getAppointments(HttpServletRequest request) {
		List<Appointment> appointments=null;
		HttpSession session=request.getSession(false);
		String doctorEmail=(String) session.getAttribute("USER_EMAIL");
		
		int DoctorId=patientDao.extract(doctorEmail).getId();
		appointments=appointmentDao.extractPatientAppointments(DoctorId);
		
		return new ResponseEntity<List<Appointment>>(appointments,HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		System.out.println("\nDoctor logout");
		HttpSession session=request.getSession(false);
		if (session != null) {
			session.invalidate(); // Invalidate the user's session
		}
		return new ResponseEntity<>("user logout",HttpStatus.OK);
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
	@PostMapping("/register")
	public ResponseEntity<Doctor> register(@RequestBody Doctor doctor) {
		System.out.println("Storing patient into db");
		System.out.println(doctor);
		
		//One exception may be patient already present with given email
		doctorDao.store(doctor); 
		return new ResponseEntity<>(doctor,HttpStatus.OK);
	}
	@PostMapping("/emailexist")
	public ResponseEntity<String> checkEmailExists(@RequestBody Map<String,String> doctor) {
		System.out.println("Checking doctor exist in DB : "+doctor.get("email"));
		doctorDao.extract(doctor.get("email"));
		System.out.println("dfgegwerg");
		throw new UserWithEmailAlreadyExistException();
	}
	
	@GetMapping("/getonlinedoctor")
	public ResponseEntity<List<Doctor>> getOnlineDoctors(@RequestParam("specialization") String specialization) {
		List<Doctor> doctors=null;
		doctors=doctorDao.extractOnineDoctor(specialization);
		return new ResponseEntity<List<Doctor>>(doctors,HttpStatus.OK);
	}
}
