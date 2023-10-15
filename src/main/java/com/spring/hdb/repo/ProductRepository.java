package com.spring.hdb.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.hdb.Entity.Product;
import com.spring.hdb.Entity.Stuff;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	List<Product> findAllBySeller(Stuff stuff);

}
