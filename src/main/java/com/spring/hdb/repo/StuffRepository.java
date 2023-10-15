package com.spring.hdb.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.hdb.Entity.Stuff;


@Repository
public interface StuffRepository extends CrudRepository<Stuff,Integer> {
	List<Stuff> findAll();
	List<Stuff> findByPhoneNumber(String phone);

}
