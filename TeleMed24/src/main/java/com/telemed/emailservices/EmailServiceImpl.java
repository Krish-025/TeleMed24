package com.telemed.emailservices;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public String sendOtp(String to) {

		// OTP generation code
		int otp = 1;
		int min = 2000, max = 6000;
		Random random = new Random();
		otp = random.nextInt(min, max);

		// OTP send code
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("TeleMed24 sign-up OTP");
		message.setText("Your OTP is " + otp);

		// Sending mail message
		mailSender.send(message);
		return otp + "";
	}

	@Override
	public void sendAppointmentConfirmationMail(String to,String patientName,String doctorName,String address,String date,String time) {
		
		String subject = "Appointment booking Confirmation Mail";
		String body = """
			    Dear, """ + patientName + """

			    We are excited to confirm your upcoming offline appointment.

			    Here are the appointment details:

			    Date: """ + date + """
			    Time: """ + time + """
			    Doctor: Dr. """ + doctorName + """ 
			    Clinic address: """ + address + """ 
			    Please arrive at the clinic a little earlier to complete any necessary paperwork and ensure a smooth consultation with the doctor.

			    We look forward to seeing you and assisting with your healthcare needs. Your well-being is our priority.

			    Thank you for choosing TeleMed24 for your healthcare services. We wish you a safe and productive appointment.

			    Best regards,

			    TeleMed24
			""";

		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		mailSender.send(mail);
	}


}
