package com.spring.hdb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hdb.Entity.Cart;
import com.spring.hdb.Entity.CartProduct;
import com.spring.hdb.Entity.Stuff;
import com.spring.hdb.service.CartService;
import com.spring.hdb.service.StuffService;

@RestController
@RequestMapping("/api")
public class StuffController {
	
	@Autowired
	StuffService stuffService;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/stuffs")
	public ResponseEntity<Object> getStuffs(){
		List<Stuff> stuffs= stuffService.getStuffs();
		List<Map<String,Object>> response =  new ArrayList<>(); 
		for(Stuff stuff :stuffs) {
			Map<String,Object> res = new HashMap<>();
			
			res.put("details", stuff);
			res.put("cart", cartService.getCartByStuff(stuff));
			
			response.add(res);
			
		}

		
		
		try {
			return new ResponseEntity<Object>(response,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		 
	}
	
	
	@GetMapping("/stuffs/{id}")
	public ResponseEntity<Object> getStuff(@PathVariable("id") int id){
		
		try {
			return new ResponseEntity<Object>(stuffService.getStuff(id),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
	}

	
	@PutMapping("/stuffs/{id}")
	public ResponseEntity<Object> updateStuffs(@PathVariable("id") int id , @RequestBody Stuff stuff){
		
		try {
			return new ResponseEntity<Object>(stuffService.UpdateStuff(id, stuff),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
	}

	
	@DeleteMapping("/stuffs/{id}")
	public ResponseEntity<Object> removeStuffs(@PathVariable("id") int id){
		
		try {
			stuffService.deleteStuff(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
	}

}
