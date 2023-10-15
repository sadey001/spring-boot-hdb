package com.spring.hdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hdb.Entity.Role;
import com.spring.hdb.repo.RoleRepository;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	RoleRepository roleRepo;
	
	@GetMapping("/roles")
	public ResponseEntity<Object> getRoles(){
		try {
			return new ResponseEntity<Object>(roleRepo.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>(roleRepo.findAll(),HttpStatus.BAD_GATEWAY);
		}
	}
	
	@GetMapping("/roles/{id}")
	public ResponseEntity<Object> getRole(@PathVariable("id") int id){
		try {
			return new ResponseEntity<Object>(roleRepo.findById(id).get(),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_GATEWAY);
		}
	}
	
	@PutMapping("/roles/{id}")
	public ResponseEntity<Object> updateRole(@PathVariable("id") int id, @RequestBody Role role){
		try {
			return new ResponseEntity<Object>(roleRepo.save(new Role(id,role.getRole())),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_GATEWAY);
		}
	}
	
	@DeleteMapping("/roles/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable("id") int id){
		try {
			roleRepo.deleteById(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_GATEWAY);
		}
	}

}
