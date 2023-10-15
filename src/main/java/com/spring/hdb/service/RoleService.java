package com.spring.hdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hdb.Entity.Role;
import com.spring.hdb.repo.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepo;
	
	public List<Role> getRoles(){
		return roleRepo.findAll();
	}
	
	public Role getRole(String role){
		List<Role> roles = roleRepo.findByRole(role);
		if(roles.size()>0) {
			return roles.get(0);
		}else {
			return null;
		}
	}
	
	public Role getRole(int id) {
		return roleRepo.findById(id).get();
	}
	
}
