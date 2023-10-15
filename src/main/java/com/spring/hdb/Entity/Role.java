package com.spring.hdb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleId;
	
	private String role;

	public Role(int id, String role) {
		super();
		this.roleId = id;
		this.role = role;
	}
	
	public Role( String role) {
		super();
		this.role = role;
	}

	public Role() {
		super();
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int id) {
		this.roleId = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
