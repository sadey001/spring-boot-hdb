package com.spring.hdb.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.hdb.Entity.CartProduct;
import com.spring.hdb.Entity.Product;

@Repository
public interface CartProductRepository extends CrudRepository<CartProduct, Integer> {
	
	List<CartProduct> findAllByProduct(Product product);

}
