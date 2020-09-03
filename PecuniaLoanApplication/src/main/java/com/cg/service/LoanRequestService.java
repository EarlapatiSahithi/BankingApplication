package com.cg.service;

import com.cg.Exceptions.CustomerNotFoundException;
import com.cg.Exceptions.LoginException;
import com.cg.dto.LoanRequestForm;




public interface LoanRequestService {

	public String createLoanRequest(LoanRequestForm loanreqform);
	public String validateTokenInLoginService(String tokenId) throws LoginException;
	
}
