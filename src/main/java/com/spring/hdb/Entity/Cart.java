package com.spring.hdb.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartId;
	
	@OneToOne
	private Stuff stuff;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name="cart_id")
	private List<CartProduct> cartProduct;
	
	private int total;

	public Cart(int cartId, Stuff stuff, List<CartProduct> cartProduct, int total) {
		super();
		this.cartId = cartId;
		this.stuff = stuff;
		this.cartProduct = cartProduct;
		this.total = total;
	}

	public Cart(int cartId, Stuff stuff, int total) {
		super();
		this.cartId = cartId;
		this.stuff = stuff;
		this.total = total;
	}
	
	public Cart( Stuff stuff, int total) {
		super();
		this.stuff = stuff;
		this.total = total;
	}

	public Cart() {
		super();
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Stuff getStuff() {
		return stuff;
	}

	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
	}

	public List<CartProduct> getCartProduct() {
		return cartProduct;
	}

	public void setCartProduct(List<CartProduct> cartProduct) {
		this.cartProduct = cartProduct;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	

}
