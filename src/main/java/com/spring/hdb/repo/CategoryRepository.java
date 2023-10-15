package com.spring.hdb.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.hdb.Entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
	
	List<Category> findAllByCategoryName(String name);

}
