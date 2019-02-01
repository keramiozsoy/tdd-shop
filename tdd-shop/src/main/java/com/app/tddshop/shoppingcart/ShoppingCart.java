package com.app.tddshop.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import com.app.tddshop.domain.Product;

public class ShoppingCart {

	private int listSize = 0;

	public int size() {
		return listSize;
	}

	public void add(Product product) {
		listSize++;
	}

	public List<Product> getCartList() {
		return new ArrayList<Product>();
	}

}
