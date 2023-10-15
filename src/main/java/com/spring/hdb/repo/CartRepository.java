package com.spring.hdb.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.hdb.Entity.Cart;
import com.spring.hdb.Entity.Stuff;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
	
	Optional<Cart> findAllByStuff(Stuff stuff);

}
