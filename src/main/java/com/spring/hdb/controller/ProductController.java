package com.spring.hdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hdb.Entity.Product;
import com.spring.hdb.Entity.Stuff;
import com.spring.hdb.service.ProductService;
import com.spring.hdb.service.StuffService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	StuffService stuffService;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return productService.getProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") int id){
		return productService.getProduct(id);
	}
	
	
	
	@PostMapping("/products/{id}")
	public Product createProduct(@PathVariable("id") int id ,@RequestBody Product product) {
		product.setSeller(stuffService.getStuff(id));
		
		return productService.addOrUpdateProduct(product);
		
	}
	
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable("id") int id , @RequestBody Product product) {
		product.setProductId(id);
		
		Product op = productService.getProduct(id);
		Stuff stuff = op.getSeller();
		product.setSeller(stuff);
		return productService.addOrUpdateProduct(product);
	}
	
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable("id") int id) {
		productService.deleteProduct(id);
		
	}
	
}
