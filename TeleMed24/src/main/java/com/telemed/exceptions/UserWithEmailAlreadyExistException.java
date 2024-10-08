package com.telemed.exceptions;

@SuppressWarnings("serial")
public class UserWithEmailAlreadyExistException extends RuntimeException {
	public UserWithEmailAlreadyExistException() {
		super();
		System.out.println("\nuser already exist");
	}
}
