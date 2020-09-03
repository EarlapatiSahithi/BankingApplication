package com.cg.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.cg.dto.Error;
import com.cg.Exceptions.CustomerNotFoundException;

@RestControllerAdvice
public class LoanAdvice {
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public Error handleNoRequestsFoundException(CustomerNotFoundException ex) {
		return new Error(HttpStatus.NOT_FOUND.toString(),ex.getMessage());
		
	}

}
