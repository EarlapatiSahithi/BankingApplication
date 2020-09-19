package com.cg.service;

import java.time.LocalDate;

import com.cg.Exceptions.CustomerNotFoundException;
import com.cg.Exceptions.LoginException;
import com.cg.dto.LoanRequestForm;
public interface LoanRequestService {

	public String createLoanRequest(LoanRequestForm loanreqform) throws CustomerNotFoundException;
	public String validateTokenInLoginService(String tokenId) throws LoginException;

	
}
