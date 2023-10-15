package com.spring.hdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.hdb.security.JWTService;

@Service
public class AuthenticationService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsService service;
	
	@Autowired
	JWTService jwtService;
	
	
	public String authenticateStuff(String phone , String Pass) {
		
		UserDetails user = service.loadUserByUsername(phone);
		
		if(passwordEncoder.matches(Pass, user.getPassword())) {
			
			return jwtService.GenerateToken(user);
		}else {
			return null;
		}
		
		
	}
	

}
