package com.capgemini.pecunia.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.dto.ErrorInfo;
import com.capgemini.pecunia.entity.User;
import com.capgemini.pecunia.exceptions.LoginException;
import com.capgemini.pecunia.service.LoginService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class LoginRestController {

	@Autowired
	private LoginService ser;
    Logger logger = LoggerFactory.getLogger(LoginRestController.class);
	
	private Map<String, User> authMap = new HashMap<>();
	
	
	@PostMapping(value = "/login")
	public String getLogin(@RequestParam("userid") String userId, 
			@RequestParam("password") String password) throws LoginException {
		logger.info("userId " + userId);
		logger.debug("user id received");
		User user = ser.doLogin(userId, password);
        String token = ser.encryptUser(user);
		authMap.put(token, user);
		return token;
	}

	@PostMapping(value = "/verifylogin")
	public String verifyLogin(@RequestBody String tokenId) throws LoginException {
		logger.info("token id" + tokenId);
		String role = null;
		User user = null;
		logger.info("auth " + authMap.containsKey(tokenId));
		if (authMap.containsKey(tokenId)) {
			user = authMap.get(tokenId);
			logger.info(user.getUserName()+ " " + user.getRole());
			return user.getRole();
		}
					
		return role;
	}
	
	@GetMapping(value = "/logout")
	public String logout(@RequestHeader("tokenId") String token,
			HttpServletRequest req) {
		authMap.remove(token);
		return "logged out";
	}

	@ExceptionHandler(LoginException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ErrorInfo handleLoginException(LoginException ex) {
		return new ErrorInfo(HttpStatus.FORBIDDEN.toString(), ex.getMessage());
	}
}
