package com.spring.hdb.Entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int productId;
	
	private String name;
	
	private String desc; 
	
	private float price;
	
	@OneToOne
	@JoinColumn(name="seller_id")
	private Stuff seller;
	
	private Date mdfDate;
	
	private Date expDate;
	
	@OneToOne(cascade=CascadeType.ALL , fetch= FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;
	

	public Product(int productId, String name, String desc, float price, Stuff seller, Date mdfDate, Date expDate,
			Category category) {
		super();
		this.productId = productId;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.seller = seller;
		this.mdfDate = mdfDate;
		this.expDate = expDate;
		this.category = category;
	}

	public Product(int productId, String name, String desc, float price, Date mdfDate, Date expDate) {
		super();
		this.productId = productId;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.mdfDate = mdfDate;
		this.expDate = expDate;
	}

	public Product() {
		super();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getMdfDate() {
		return mdfDate;
	}

	public void setMdfDate(Date mdfDate) {
		this.mdfDate = mdfDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	
	public Stuff getSeller() {
		return seller;
	}

	public void setSeller(Stuff seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", desc=" + desc + ", price=" + price
				+ ", mdfDate=" + mdfDate + ", expDate=" + expDate + "]";
	}

}
