package com.cg.service;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.cg.Exceptions.CustomerNotFoundException;
import com.cg.Exceptions.LoginException;
import com.cg.controller.LoanReqControllerClass;
import com.cg.dao.CustomerDao;
import com.cg.dao.LoanRequestDao;
import com.cg.dto.LoanRequestForm;
import com.cg.entity.Customer;
import com.cg.entity.LoanRequest;
import com.cg.util.CgConstants;
@Service
public class LoanRequestServiceImp implements LoanRequestService {
	Logger logger = LoggerFactory.getLogger(LoanReqControllerClass.class);
	@Autowired
	LoanRequestDao lrdao;
	@Autowired
	CustomerDao custdao;
	@Autowired
	private RestTemplate rt;
	@Override
	@Transactional(readOnly=false)
	public String createLoanRequest(LoanRequestForm loanreqform) throws CustomerNotFoundException   {
		LoanRequest loanrequest=new LoanRequest();
			Customer customer = new Customer();
		LocalDateTime now = LocalDateTime.now();
		LocalDate now1=LocalDate.now();
		String loanID = CgConstants.LOAN+loanreqform.getCustomerId()+now.getSecond();
		loanrequest.setLoanRequestId(loanID);
		loanrequest.setLoanAmount(loanreqform.getLoanAmount());
		loanrequest.setLoanType(loanreqform.getLoanType());
		loanrequest.setLoanTenure(loanreqform.getLoanTenure());
		loanrequest.setReqStatus(CgConstants.PENDING_REQUEST);
		loanrequest.setDateOfRequest(LocalDate.now());
		loanrequest.setAnnualIncome(loanreqform.getAnnualIncome());
		Customer cust=custdao.getCustomer(loanreqform.getCustomerId());
		loanrequest.setCustomer(cust);
		    LoanRequest persistedLr=lrdao.save(loanrequest);
		    Optional<Customer> optcustomer=custdao.findById(loanreqform.getCustomerId());
			if (!optcustomer.isPresent())
				throw new CustomerNotFoundException(CgConstants.CUSTOMER_NOT_FOUND);
			return CgConstants.LOAN_REQUEST_CREATED+ loanID;
		}

		@Override
		public String validateTokenInLoginService(String tokenId) throws LoginException {
			if(tokenId==null||tokenId.length()==0)
				throw new LoginException(CgConstants.USER_NOT_AUTHORIZED);
			String url=CgConstants.LOGIN_MAIN_URL;
			String role=rt.postForObject(url, tokenId, String.class);
			logger.info(CgConstants.ROLE + role);
			if(role==null)
				throw new LoginException(CgConstants.USER_NOT_AUTHENTICATED);
			return role;
		}
		

}
