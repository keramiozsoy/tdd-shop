package com.app.tddshop.shoppingcart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.app.tddshop.domain.Category;
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

	public double getTotalAmountAfterDiscount() {
		return 0; 
	}

	public Map<Product, Integer> countFrequencies(List<Product> shoppingCartList) {
		Map<Product, Integer> countMap = new HashMap<>();
		Set<Product> pList = new HashSet<Product>(shoppingCartList);
		for (Product p : pList) {
//			System.out.println(p + ": " + Collections.frequency(shoppingCartList, p));
			countMap.put(p,Collections.frequency(shoppingCartList, p));
		}
		return countMap;
	}

}
