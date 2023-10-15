package com.spring.hdb;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.hdb.Entity.Role;
import com.spring.hdb.Entity.Stuff;
import com.spring.hdb.repo.RoleRepository;
import com.spring.hdb.repo.StuffRepository;

@SpringBootTest
class SpringBootHdbApplicationTests {
	
	@Autowired
	StuffRepository stuffRepo;
	
	@Autowired
	RoleRepository roleRepo;

	@Test
	void test1() {
		assertEquals(0,stuffRepo.findAll().size());
		assertEquals(0,roleRepo.findAll().size());
	}
	
	
	@Test
	void test2() {
//		stuffRepo.save(new Stuff("sanat",80000,new Role("LEAD")));
		assertEquals(0,stuffRepo.findAll().size());
		assertEquals(0,roleRepo.findAll().size());
	}

}
