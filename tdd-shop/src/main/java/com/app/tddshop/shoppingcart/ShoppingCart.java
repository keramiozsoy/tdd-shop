package com.app.tddshop.shoppingcart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;

import com.app.tddshop.domain.Category;
import com.app.tddshop.domain.Product;

public class ShoppingCart {

	private List<Product> cartList = new ArrayList<>();
	private Optional<Product> productParam = Optional.empty();

	public int size() {
		return cartList.size();
	}

	public void add(Product product, int count) {
		if (checkRequiredPriceInfoForAddingProduct(product) && checkRequiredCategoryInfoForAddingProduct(product)) {
			for (int i = 0; i < count; i++) {
				cartList.add(product);
			}
		}

	}

	public List<Product> getCartList() {
		return cartList;
	}

	public double getTotalAmount() {
		double sum = cartList.stream().mapToDouble(p -> p.getPrice()).sum();

		return sum;
	}

	public boolean checkRequiredPriceInfoForAddingProduct(Product product) {
		productParam = Optional.ofNullable(product); // ofNullable null kabul eder exception fırlatmaz
		Optional<Double> price = Optional.empty();

		if (productParam.isPresent()) {
			price = Optional.ofNullable(productParam.get().getPrice());
		}

		return price.isPresent();
	}

	public boolean checkRequiredCategoryInfoForAddingProduct(Product product) {
		productParam = Optional.ofNullable(product); // ofNullable null kabul eder exception fırlatmaz
		Optional<Category> category = Optional.empty();

		if (productParam.isPresent()) {
			category = Optional.ofNullable(productParam.get().getCategory());
		}

		return category.isPresent();
	}

	public double getTotalAmountAfterDiscount() {
		return 0;
	}

	public Map<Category, Integer> countFrequencies(List<Product> shoppingCartList) {
		List<Category> shoppingCartCategoryList = new ArrayList<>();
		for (Product product : shoppingCartList) {
			shoppingCartCategoryList.add(product.getCategory());
		}

		Map<Category, Integer> countMap = new HashMap<>();
		for (Category category : shoppingCartCategoryList) {
			countMap.put(category, Collections.frequency(shoppingCartCategoryList, category));
		}

		return countMap;
	}

	public boolean hasSameCategoryInFrequencies(Map<Category, Integer> resultFrequencies) {
		boolean result = true;
		for (Entry<Category, Integer> item : resultFrequencies.entrySet()) {
			Integer frequency = resultFrequencies.get(item.getKey());
			if (frequency.intValue() < 2) {
				result = false;
			}
		}
		return result;
	}

	public Map<Category, Integer> hasCategoryCountGreaterThanThreeInFrequencies(
			Map<Category, Integer> resultFrequenciesForGreaterThanThree) {
		Map<Category, Integer> resultList = new HashMap<>();
		for (Entry<Category, Integer> item : resultFrequenciesForGreaterThanThree.entrySet()) {
			Integer frequency = resultFrequenciesForGreaterThanThree.get(item.getKey());
			if (frequency > 3) {
				resultList.put(item.getKey(), item.getValue());
			}
		}
		return resultList;
	}

	public Map<Category, Integer> hasCategoryCountGreaterThanFiveInFrequencies(Map<Category, Integer> resultFrequenciesForGreaterThanFive) {
		Map<Category, Integer> resultList = new HashMap<>();
		for (Entry<Category, Integer> item : resultFrequenciesForGreaterThanFive.entrySet()) {
			Integer frequency = resultFrequenciesForGreaterThanFive.get(item.getKey());
			if (frequency > 5) {
				resultList.put(item.getKey(), item.getValue());
			}
		}
		return resultList;
	}
}
