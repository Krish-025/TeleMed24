package com.telemed.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> emailNotFound(UserNotFoundException exception) {
		return new ResponseEntity<String>("user not found",HttpStatus.OK);
	}
	
	@ExceptionHandler(UserWithEmailAlreadyExistException.class)
	public ResponseEntity<String> UserWithEmailAlreadyExistException(UserWithEmailAlreadyExistException exception) {
		return new ResponseEntity<String>("user already exist",HttpStatus.OK);
	}
	
	@ExceptionHandler(NoSlotsAvailable.class) 
	public ResponseEntity<String> noTimeSlotAvailableForAppointment (NoSlotsAvailable exception) {
		return new ResponseEntity<String>("no slots available",HttpStatus.OK);
	}
}
