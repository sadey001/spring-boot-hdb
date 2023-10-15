package com.spring.hdb.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter{
	
	

	UserDetailsService userService;
	
	JWTService jwtService;

	public JwtAuthFilter(UserDetailsService userDetailsService, JWTService jwtService) {
		this.userService = userDetailsService;
		this.jwtService = jwtService; 
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getAuthHeader(request);
		String username ;
		
		if(token != null) {
			username = jwtService.ValidateToken(token);
			if("-1".equals(username)) {
				System.out.println("Token Expired");
			}else if(username == null) {
				System.out.println("Token Invalid");
			}else {
				try {
					UserDetails user = userService.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user.getUsername(),null,user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(userToken);
					System.out.println("Authenticated User : "+username);
				}catch(Exception e) {
					throw new UsernameNotFoundException("Username not found : "+username);
				}
				
			}
					
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String getAuthHeader(HttpServletRequest request) {
		
		String header = request.getHeader("Authorization");
		
		if( header != null  && !header.isBlank() && header.startsWith("Bearer ")) {
			return header.substring(7);
		}
		
		return null;
	}

}
