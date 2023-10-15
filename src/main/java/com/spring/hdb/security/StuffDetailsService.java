package com.spring.hdb.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.hdb.Entity.Role;
import com.spring.hdb.Entity.Stuff;
import com.spring.hdb.repo.StuffRepository;

@Service
public class StuffDetailsService implements UserDetailsService {
	
	@Autowired
	StuffRepository stuffrepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Stuff> stuffs = stuffrepo.findByPhoneNumber(username);
		
		UserDetails user;
		
		List<GrantedAuthority> auths = new ArrayList<>();
		
		
		if(stuffs.size() > 0) {
			for(Role role:stuffs.get(0).getRoles()) {
				auths.add(new SimpleGrantedAuthority(role.getRole()));	
			}
			user = User.withUsername(stuffs.get(0).getPhoneNumber())
					.password(stuffs.get(0).getPassword())
					.authorities(auths)
					.build();
					
			return user;
			
		}else {
			throw new UsernameNotFoundException(username+ " is not Found");
		}
		
		
	}



}
