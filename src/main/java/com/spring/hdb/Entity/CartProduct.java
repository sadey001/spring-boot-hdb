package com.spring.hdb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class CartProduct {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cpId;
	

	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	private int qty;

	public CartProduct(int cpId, Cart cart, Product product, int qty) {
		super();
		this.cpId = cpId;
		this.product = product;
		this.qty = qty;
	}

	public CartProduct() {
		super();
	}

	public int getCpId() {
		return cpId;
	}

	public void setCpId(int cpId) {
		this.cpId = cpId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
