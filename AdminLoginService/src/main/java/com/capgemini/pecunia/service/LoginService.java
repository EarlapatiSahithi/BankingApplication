package com.capgemini.pecunia.service;

import com.capgemini.pecunia.entity.User;
import com.capgemini.pecunia.exceptions.LoginException;

public interface LoginService {
    public User doLogin(String userId, String password)throws LoginException;
    public String encryptUser(User user);
    
}
