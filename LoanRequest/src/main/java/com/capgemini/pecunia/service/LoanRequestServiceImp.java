package com.capgemini.pecunia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.dao.CustomerDao;
import com.capgemini.pecunia.dao.LoanRequestDao;
import com.capgemini.pecunia.dto.LoanRequestDto;
import com.capgemini.pecunia.entity.Customer;
import com.capgemini.pecunia.entity.LoanRequest;
import com.capgemini.pecunia.exception.BankAccountNotFound;
@Service
public class LoanRequestServiceImp implements LoanRequestService {
	@Autowired
	LoanRequestDao lrdao;
	
	@Autowired
	CustomerDao custdao;
	
	/*@Override
	public String loanRequest(LoanRequest loanreq) {
		Customer s1=loanreq.getCustomer();
	Customer cus=new Customer();
	loanreq.setLoanRequestId(loanreq.getLoanRequestId());
	loanreq.setLoanAmount(loanreq.getLoanAmount());
	loanreq.setLoanType(loanreq.getLoanType());
	loanreq.setLoanTenure(loanreq.getLoanTenure());
	loanreq.setReqStatus(loanreq.getReqStatus());
	loanreq.setDateOfRequest(loanreq.getDateOfRequest());
	loanreq.setAnnualIncome(loanreq.getAnnualIncome());
	cus.setCustomerId(cus.getCustomerId());
	

	}*/
	@Override
	@Transactional(readOnly=false)
	public String createLoanRequest(LoanRequestDto loanreqdto) {
		LoanRequest loanrequest=new LoanRequest();
		loanrequest.setLoanRequestId(loanreqdto.getLoanRequestId());
		loanrequest.setLoanAmount(loanreqdto.getLoanAmount()	);
		loanrequest.setLoanType(loanreqdto.getLoanType());
		loanrequest.setLoanTenure(loanreqdto.getLoanTenure());
		loanrequest.setReqStatus(loanreqdto.getReqStatus());
		loanrequest.setDateOfRequest(loanreqdto.getDateOfRequest());
		loanrequest.setAnnualIncome(loanreqdto.getAnnualIncome());
		Customer cust=custdao.getCustomer(loanreqdto.getCustomerId());
		loanrequest.setCustomer(cust);
	    LoanRequest persistedLr=lrdao.save(loanrequest);
		return "Loan Request Created";
	}

}
