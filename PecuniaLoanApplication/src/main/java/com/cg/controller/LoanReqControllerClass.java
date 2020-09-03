package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.LoanRequestDto;
import com.cg.service.LoanRequestService;
import com.cg.util.CgConstants;

@RestController

public class LoanReqControllerClass {
	@Autowired
	LoanRequestService service;

	@PostMapping(CgConstants.LOAN_REQUEST_URL)
	public String createLoanRequest(@RequestBody LoanRequestDto loanreqdto) {
		
			String loanRequestId=service.createLoanRequest(loanreqdto);
			return CgConstants.LOAN_REQUEST_CREATED;
		}
	}


