package com.spring.hdb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsservice;
	
	@Autowired
	JWTService jwtService;
	
	@Bean
	public JwtAuthFilter filter() {
		return new JwtAuthFilter(userDetailsservice,jwtService);
	}
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests().requestMatchers(new MvcRequestMatcher(null, "/auth/**")).permitAll()
									.requestMatchers(new MvcRequestMatcher(null, "/api/account/**")).hasAnyAuthority("STUFF","ADMIN")
									.requestMatchers(new MvcRequestMatcher(null, "/api/stuffs/**"),new MvcRequestMatcher(null , "/api/products/**"),new MvcRequestMatcher(null , "/api/roles/**")).hasAuthority("ADMIN")
									.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
			http.authorizeHttpRequests().anyRequest().authenticated();
		http.addFilterBefore(new JwtAuthFilter(userDetailsservice,jwtService), UsernamePasswordAuthenticationFilter.class);
		http.headers().frameOptions().disable();
		//http.authorizeHttpRequests().anyRequest().permitAll();
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
