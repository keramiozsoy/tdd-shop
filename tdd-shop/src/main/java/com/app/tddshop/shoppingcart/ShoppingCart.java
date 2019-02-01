package com.app.tddshop.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import com.app.tddshop.domain.Product;

public class ShoppingCart {

	private int listSize = 0;
	private List<Product> cartList = new ArrayList<>(); // static - yeni referans obje referans almasın

	public int size() {
		return listSize;
	}

	public void add(Product product) {
		listSize++;
		cartList.add(product);
	}

	public List<Product> getCartList() {
		return cartList;
	}

}
