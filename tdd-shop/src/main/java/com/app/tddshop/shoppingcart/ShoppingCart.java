package com.app.tddshop.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import com.app.tddshop.domain.Product;

public class ShoppingCart {

	private List<Product> cartList = new ArrayList<>(); // static - yeni referans obje referans almasın

	public int size() {
		return cartList.size();
	}

	public void add(Product product) {
		cartList.add(product);
	}

	public List<Product> getCartList() {
		return cartList;
	}

}
