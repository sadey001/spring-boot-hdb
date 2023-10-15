package com.spring.hdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.hdb.Entity.Role;
import com.spring.hdb.Entity.Stuff;
import com.spring.hdb.repo.StuffRepository;

@Service
public class StuffService {
	
	@Autowired
	StuffRepository stuffRepo;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<Stuff> getStuffs(){
		return stuffRepo.findAll();
	}

	public Stuff InsertStuff(Stuff stuff) {
		String pass = stuff.getPassword();
		stuff.setPassword(passwordEncoder.encode(pass));
		return stuffRepo.save(stuff);
	}
	
//	public Stuff UpdateStuff(int id,Stuff stuff) {
//		
//		Stuff newStuff = stuffRepo.findById(id).get();
//
//		if(stuff.getName()!= null) {
//			newStuff.setName(stuff.getName());
//		}
//		if(stuff.getSalary() !=  0) {
//			newStuff.setSalary(stuff.getSalary());
//		}
//		
//		return stuffRepo.save(newStuff);
//	}
	
	public Stuff UpdateStuff(int id,Stuff stuff) {
		
		Stuff newStuff = stuffRepo.findById(id).get();

		if(stuff.getName()!= null) {
			newStuff.setName(stuff.getName());
		}
		if(stuff.getSalary() !=  0) {
			newStuff.setSalary(stuff.getSalary());
		}
		if(stuff.getRoles()!= null && stuff.getRoles().size()>0) {
			List<Role> rs = newStuff.getRoles();
			for(Role role:stuff.getRoles()) {
				for(Role r: rs) {
					if(role.getRoleId()== r.getRoleId()) {
						r.setRole(role.getRole());
					}
				}
			}
			newStuff.setRoles(rs);
		}
		
		return stuffRepo.save(newStuff);
	}
	
	public Stuff getStuff(Authentication authentication) {
		
		String username = authentication.getName();
		
		return stuffRepo.findByPhoneNumber(username).get(0);
		
	}
	
	
	public Stuff getStuff(int id) {
		
		return stuffRepo.findById(id).get();
		
	}
	
	public Stuff updateStuff(Authentication authentication,Stuff stuff) {
		
		String username = authentication.getName();
		
		return UpdateStuff(stuffRepo.findByPhoneNumber(username).get(0).getId(),stuff);
		
	}
	
	public void deleteStuff(int id) {
		stuffRepo.deleteById(id);
	}
	
	
}









