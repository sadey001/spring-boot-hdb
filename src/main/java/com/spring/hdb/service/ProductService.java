package com.spring.hdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hdb.Entity.Category;
import com.spring.hdb.Entity.Product;
import com.spring.hdb.Entity.Stuff;
import com.spring.hdb.repo.CategoryRepository;
import com.spring.hdb.repo.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	CategoryRepository cateRepo;
	
	//categories
	
	public List<Category> getCategories() {
		return (List<Category>)cateRepo.findAll();
	}
	
	public Category addOrUpdateCategory(Category category) {
		return cateRepo.save(category);
	}
	
	public Category getCategory(String name) {
		List<Category> cats = cateRepo.findAllByCategoryName(name);
		if(cats.size()>0) {
			return cats.get(0);
		}else {
			return null;
		}
	}
	
	public Category getCategory(int id) {
		return cateRepo.findById(id).get();
	}
	
	public void deleteCategory(int id) {
		cateRepo.deleteById(id);
	}
	
	//Products
	
	public List<Product> getProducts() {
		return (List<Product>)productRepo.findAll();
	}
	
	public Product addOrUpdateProduct(Product product) {
		return productRepo.save(product);
	}
	
	public Product getProduct(int id) {
		return productRepo.findById(id).get();
	}
	
	public List<Product> getMyProducts(Stuff stuff) {
		return productRepo.findAllBySeller(stuff);
	}
	
	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}
	
	
	
}
