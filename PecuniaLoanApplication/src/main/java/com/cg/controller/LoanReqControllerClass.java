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
//@RequestMapping("/createloanreq")
//@CrossOrigin("http://localhost:4200")
public class LoanReqControllerClass {
	@Autowired
	LoanRequestService service;

	// this method is used to request a loan, this takes the loan request object as
	// the input, checks whether the object is  null and then passes to service layer
	@PostMapping(CgConstants.LOAN_REQUEST_URL)
	public String createLoanRequest(@RequestBody LoanRequestDto loanreqdto) throws Exception {
		//if (loanreq == null) {
			//throw new RuntimeException("object is null");
		//} else {
			String loanRequestId=service.createLoanRequest(loanreqdto);
			return CgConstants.LOAN_REQUEST_CREATED;
		}
	}


