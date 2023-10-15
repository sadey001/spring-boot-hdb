package com.spring.hdb.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="stuff")
public class Stuff {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(nullable = false, unique = true)
	private String phoneNumber;
	
	private int salary;
	
	@Column(nullable = false)
	private String password;
	
//	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	@JoinColumn(name="cart_id")
//	private Cart cart;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToMany(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name = "stuff_id")
	private List<Role> roles = new ArrayList<>();
	
	

//	public Stuff(int id, String name, String phoneNumber, int salary, String password, Cart cart, Address address,
//			List<Role> roles) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.phoneNumber = phoneNumber;
//		this.salary = salary;
//		this.password = password;
//		this.cart = cart;
//		this.address = address;
//		this.roles = roles;
//	}

	public Stuff(int id, String name, String phoneNumber, int salary, String password, Address address,
			List<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
		this.password = password;
		this.address = address;
		this.roles = roles;
	}

	public Stuff(int id, String name,String phoneNumber , String password ,int salary, List<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.roles = roles;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public Stuff( String name,String phoneNumber,String password, int salary, Role role) {
		super();
		this.name = name;
		this.salary = salary;
		this.roles = new ArrayList<>(Arrays.asList(role));
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public Stuff() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles=roles;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

//	public Cart getCart() {
//		return cart;
//	}
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}
//	
	

}
