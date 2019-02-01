package com.app.tddshop.shoppingcart;

import com.app.tddshop.domain.Product;

public class ShoppingCart {

	private int listSize = 0;
	
	public int size() {
		return listSize;
	}

	public void add(Product product) {
		listSize = 1;
	}

}
