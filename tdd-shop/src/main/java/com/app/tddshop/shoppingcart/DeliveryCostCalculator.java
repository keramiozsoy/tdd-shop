package com.app.tddshop.shoppingcart;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.app.tddshop.domain.Category;
import com.app.tddshop.domain.Product;

public class DeliveryCostCalculator {

	private int numberOfDeliveries = 0; //distinct category count
	private int numberOfProducts = 0; 	//distinct product count
	private BigDecimal costPerDelivery;
	private BigDecimal costPerProduct;
	private BigDecimal fixedCost;
	private BigDecimal resultCalculation = new BigDecimal(BigInteger.ZERO);

	public DeliveryCostCalculator(BigDecimal costPerDelivery, BigDecimal costPerProduct, BigDecimal fixedCost) {
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
		this.fixedCost = fixedCost;
	}

	public double calculateFor(ShoppingCart shoppingCart) {
		Map<Category, Integer> resultDistincCategory = shoppingCart.countFrequencies(shoppingCart.getCartList());
		numberOfDeliveries = resultDistincCategory.size(); 

		Set<Product> resultDisctintProduct = new HashSet<Product>(shoppingCart.getCartList());
		numberOfProducts = resultDisctintProduct.size();

		BigDecimal delivery = costPerDelivery.multiply(new BigDecimal(numberOfDeliveries).setScale(2, RoundingMode.HALF_UP));
		BigDecimal product = costPerProduct.multiply(new BigDecimal(numberOfProducts).setScale(2, RoundingMode.HALF_UP));
		resultCalculation = delivery.add(product).add(fixedCost).setScale(2, RoundingMode.HALF_UP);;
		return resultCalculation.doubleValue();
	}

}
