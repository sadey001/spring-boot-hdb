package com.spring.hdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.spring.hdb.Entity.Cart;
import com.spring.hdb.Entity.CartProduct;
import com.spring.hdb.Entity.Product;
import com.spring.hdb.Entity.Stuff;
import com.spring.hdb.repo.CartProductRepository;
import com.spring.hdb.repo.CartRepository;
import com.spring.hdb.repo.StuffRepository;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartrepo;
	
	@Autowired
	ProductService service;
	
	@Autowired
	StuffRepository stuffRepo;
	
	@Autowired
	CartProductRepository cpRepo;

	public Cart getCartByStuff(Stuff stuff) {
		return  cartrepo.findAllByStuff(stuff).get(); 
	}
	
	public Cart createOrUpdateCart(Cart cart) {
		return cartrepo.save(cart);
	}
	
	public Cart getCart(Authentication auth) {
		Stuff stuff = stuffRepo.findByPhoneNumber(auth.getName()).get(0);
		return cartrepo.findAllByStuff(stuff).get();
	}
	
	public Cart addCartProduct(Stuff stuff,CartProduct cp) {
		Cart cart = getCartByStuff(stuff);
		List<CartProduct> cps = cart.getCartProduct();
		Product product = service.getProduct(cp.getProduct().getProductId());
		cp.setProduct(product);
		cps.add(cp);
		cart.setCartProduct(cps);
		int total=0;
		for(CartProduct ncp : cps) {
			 total =total + (cp.getQty() * (int)ncp.getProduct().getPrice());
		}
		cart.setTotal(total);
		return createOrUpdateCart(cart);
	}
	
	public Cart removeFromCart(Stuff stuff , int cpid,int qty1) {
		Cart cart = getCartByStuff(stuff);
		List<CartProduct> cps = cart.getCartProduct();
		CartProduct cp = cpRepo.findById(cpid).get();
		int qty = (cp.getQty() - qty1);
		if(qty <= 0) {
			cpRepo.deleteById(cpid);
			cps.remove(cpRepo.findById(cpid).get());
		}else {
			cp.setQty(qty);
			cpRepo.save(cp);
		}
		
		int total = 0;
		for(CartProduct ncp : cps) {
			 total =total + (ncp.getQty() * (int)ncp.getProduct().getPrice());
		}
		
		cart.setTotal(total);
		
		return createOrUpdateCart(cart);
		
	}
}
