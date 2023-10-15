package com.spring.hdb.controller;

import java.util.List;

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

import com.spring.hdb.Entity.CartProduct;
import com.spring.hdb.Entity.Product;
import com.spring.hdb.Entity.Stuff;
import com.spring.hdb.service.CartService;
import com.spring.hdb.service.ProductService;
import com.spring.hdb.service.StuffService;


@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	StuffService stuffService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/account")
	public ResponseEntity<Object> getStuff(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			return new ResponseEntity<Object>(stuffService.getStuff(authentication),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		 
	}
	
	@PutMapping("/account")
	public ResponseEntity<Object> updateStuffs(@RequestBody Stuff stuff){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			return new ResponseEntity<Object>(stuffService.updateStuff(authentication, stuff),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
		 
	}
	
	@DeleteMapping("/account")
	public ResponseEntity<Object> removeStuffs(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			stuffService.deleteStuff(stuffService.getStuff(authentication).getId());
			return new ResponseEntity<Object>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
	}
	
	@GetMapping("/account/products")
	public List<Product> getMyProducts(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Stuff stuff = stuffService.getStuff(authentication);
		return productService.getMyProducts(stuff);
	}
	
	
	@GetMapping("/account/cart")
	public ResponseEntity<Object> getCart(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Stuff stuff = stuffService.getStuff(authentication);
		try {
			return new ResponseEntity<Object>(cartService.getCartByStuff(stuff),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		 
	}
	
	
	@PostMapping("/account/cart")
	public ResponseEntity<Object> addCartProduct(@RequestBody CartProduct cp){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Stuff stuff = stuffService.getStuff(authentication);
		try {
			return new ResponseEntity<Object>(cartService.addCartProduct(stuff, cp),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		 
	}
	
	@DeleteMapping("/account/cart/{cart}/cp/{cp}")
	public ResponseEntity<Object> deleteCartProduct(@PathVariable("cart") int cartId ,@PathVariable("cp") int cpId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Stuff stuff = stuffService.getStuff(authentication);
		try {
			return new ResponseEntity<Object>(cartService.removeFromCart(stuff,cartId, cpId),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		 
	}

}
