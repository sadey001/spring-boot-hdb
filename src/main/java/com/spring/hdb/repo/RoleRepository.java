package com.spring.hdb.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.hdb.Entity.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	List<Role> findAll();
	
	List<Role> findByRole(String role);

}
