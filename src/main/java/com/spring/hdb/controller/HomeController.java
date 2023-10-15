package com.spring.hdb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hdb.Entity.Cart;
import com.spring.hdb.Entity.Stuff;
import com.spring.hdb.service.AuthenticationService;
import com.spring.hdb.service.CartService;
import com.spring.hdb.service.StuffService;

@RestController
public class HomeController {
	
	@Autowired
	StuffService service;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	AuthenticationService authService;
	
	
	@GetMapping("/")
	public ResponseEntity testing() {
		HashMap<String, String> body = new HashMap<String,String>();
		body.put("message", "Applicatiion Up and Running");
		return new ResponseEntity<>(body,HttpStatus.OK);
	}
	
	
	@PostMapping("/auth/register")
	public ResponseEntity registerStuff(@RequestBody Stuff stuff) {
		
		Map<String,Object> response = new HashMap<>();
		
		response.put("details", service.InsertStuff(stuff));
		response.put("cart", cartService.createOrUpdateCart(new Cart(stuff,0)));
		
		try {
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity authenticateUser(@RequestBody Stuff stuff) {
		Map<String,String> rs = new HashMap<>();
		rs.put("jwt", authService.authenticateStuff(stuff.getPhoneNumber(), stuff.getPassword()));	
		return new ResponseEntity<Object>(rs,HttpStatus.OK);
	}
}
