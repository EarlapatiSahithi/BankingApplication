package com.cg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.cg.Exceptions.*;

import com.cg.dto.LoanReqMsg;
import com.cg.dto.LoanRequestForm;
import com.cg.service.LoanRequestService;
import com.cg.util.CgConstants;

@RestController
@CrossOrigin(origins={"http://localhost:4200"})
public class LoanReqControllerClass {
	Logger logger = LoggerFactory.getLogger(LoanReqControllerClass.class);
	@Autowired
	LoanRequestService service;
	@Autowired
	private RestTemplate rt;
	@PostMapping(CgConstants.LOAN_REQUEST_URL)
	public LoanReqMsg createLoanRequest(@RequestHeader(name="tokenId",required=false) String tokenId, @RequestBody LoanRequestForm loanreqform) throws  LoginException,CustomerNotFoundException  {
		logger.info(CgConstants.TOKEN_ID+ tokenId);
		String role=service.validateTokenInLoginService(tokenId);
logger.info(CgConstants.ROLE+role);
			String loanRequestId=service.createLoanRequest(loanreqform);
			return new LoanReqMsg(loanRequestId);
		}
	}


