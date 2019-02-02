package com.app.tddshop.shoppingcart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.tddshop.domain.Product;

public class ShoppingCart {

	private List<Product> cartList = new ArrayList<>();

	public int size() {
		return cartList.size();
	}

	public void add(Product product) {
		if (checkRequiredInfoForAddingProduct(product)) {
			cartList.add(product);
		}
	}

	public List<Product> getCartList() {
		return cartList;
	}

	public double getTotalAmount() {
		double sum = cartList.stream().mapToDouble(p -> p.getPrice()).sum();

		return sum;
	}

	public boolean checkRequiredInfoForAddingProduct(Product product) {
		Optional<Product> productParam = Optional.ofNullable(product); // ofNullable null kabul eder exception fırlatmaz
		Optional<Double> price = Optional.empty();

		if (productParam.isPresent()) {
			price = Optional.ofNullable(productParam.get().getPrice());
		}

		return price.isPresent();
	}

}
