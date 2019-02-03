package com.app.tddshop.shoppingcart;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.app.tddshop.domain.Category;
import com.app.tddshop.domain.Product;

public class ShoppingCart {

	private List<Product> cartList = new ArrayList<>();
	private Optional<Product> productParam = Optional.empty();
	private BigDecimal totalDiscountVal = new BigDecimal(BigInteger.ZERO); // Tüm indirim tutarlarının toplamı
	private BigDecimal totalCouponDicountVal = new BigDecimal(BigInteger.ZERO); // Tüm indirim tutarlarının toplamı

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
		List<BigDecimal> bList = new ArrayList<>();
		cartList.forEach(p -> bList.add(p.getPrice().setScale(2, RoundingMode.HALF_UP)));

		BigDecimal sum = bList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

		return sum.doubleValue();
	}

	public double getTotalAmountAfterDiscount() {
		BigDecimal realTotalAmount = new BigDecimal(getTotalAmount()).setScale(2, RoundingMode.HALF_UP);
		BigDecimal realTotalAmountAfterDiscount = realTotalAmount.subtract(totalDiscountVal).setScale(2, RoundingMode.HALF_UP);
		return realTotalAmountAfterDiscount.doubleValue();
	}

	public double getTotalCouponDiscount() { // uygulanan toplam kupon indirim miktarı
		return totalCouponDicountVal.doubleValue();
	}

	public double getTotalAmountCouponDiscount() { // Kupon uygulandıktan sonraki total amount
		BigDecimal realTotalAmount = new BigDecimal(getTotalAmount()).setScale(2, RoundingMode.HALF_UP);
		BigDecimal realTotalAmountAfterDiscount = realTotalAmount.subtract(totalCouponDicountVal).setScale(2, RoundingMode.HALF_UP);
		return realTotalAmountAfterDiscount.doubleValue();
	}

	public boolean checkRequiredPriceInfoForAddingProduct(Product product) {
		productParam = Optional.ofNullable(product); // ofNullable null kabul eder exception fırlatmaz
		Optional<BigDecimal> price = Optional.empty();

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

	public Map<Category, Integer> countFrequencies(List<Product> shoppingCartList) {
		List<Category> shoppingCartCategoryList = new ArrayList<>();
		for (Product product : shoppingCartList) {
			shoppingCartCategoryList.add(product.getCategory());
		}

		Map<Category, Integer> countMap = new HashMap<>();
		for (Category category : shoppingCartCategoryList) {
			countMap.put(category, Collections.frequency(shoppingCartCategoryList, category));
		}

		Map<Category, Integer> sortedMap = countMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new /** LinkedHashmap olmadan çalışmaz */
				));

		return sortedMap;
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

	public Map<Category, Integer> hasCategoryCountGreaterThanExpectedCountInFrequencies(
			Map<Category, Integer> resultFrequenciesForGreaterThanExpectedCount, int expectedCount) {
		Map<Category, Integer> resultList = new HashMap<>();
		for (Entry<Category, Integer> item : resultFrequenciesForGreaterThanExpectedCount.entrySet()) {
			Integer frequency = resultFrequenciesForGreaterThanExpectedCount.get(item.getKey());
			if (frequency > expectedCount) {
				resultList.put(item.getKey(), item.getValue());
			}
		}
		return resultList;
	}

	public void applyDiscount(Map<Category, Integer> resultOrderedFrequenciesForApplyCampaignDiscount) {

		Map<Category, Integer> val = resultOrderedFrequenciesForApplyCampaignDiscount;

		Map<Category, Integer> returnMap = this
				.hasCategoryCountGreaterThanExpectedCountInFrequencies(resultOrderedFrequenciesForApplyCampaignDiscount, 5);

		applyDiscountFiftyPercent(returnMap);
		applyRemove(val, returnMap);

		Map<Category, Integer> returnMap2 = this
				.hasCategoryCountGreaterThanExpectedCountInFrequencies(resultOrderedFrequenciesForApplyCampaignDiscount, 3);

		applyDiscountTwentyPercent(returnMap2);
		applyRemove(val, returnMap2);

		Map<Category, Integer> returnMap3 = this
				.hasCategoryCountGreaterThanExpectedCountInFrequencies(resultOrderedFrequenciesForApplyCampaignDiscount, 1);

		applyDiscountFiveLira(returnMap3);
		applyRemove(val, returnMap3);

	}

	private void applyDiscountTwentyPercent(Map<Category, Integer> returnMap) {
		if (!returnMap.isEmpty()) {
			discountPercent(returnMap, 0.2);
		}
	}

	private void applyDiscountFiftyPercent(Map<Category, Integer> returnMap2) {
		if (!returnMap2.isEmpty()) {
			discountPercent(returnMap2, 0.5);
		}
	}

	private void applyDiscountFiveLira(Map<Category, Integer> returnMap3) {
		if (!returnMap3.isEmpty()) {
			discountLira(returnMap3, 5.0);
		}
	}

	private void discountPercent(Map<Category, Integer> rm, double percent) {
		BigDecimal discountAmount = sumCategory(rm).multiply(new BigDecimal(percent)).setScale(2, RoundingMode.HALF_UP);
		totalDiscountVal = totalDiscountVal.add(discountAmount);// indirim yapılacak tutar
	}

	private void discountLira(Map<Category, Integer> rm, double lira) {
		BigDecimal discountAmount = sumCategory(rm).subtract(new BigDecimal(lira)).setScale(2, RoundingMode.HALF_UP);
		totalDiscountVal = totalDiscountVal.add(discountAmount); // indirim yapılacak tutar
	}

	private BigDecimal sumCategory(Map<Category, Integer> rm) {
		Map.Entry<Category, Integer> entry = rm.entrySet().iterator().next();
		Category key = entry.getKey(); // işlem yapacağım kategori

		List<Product> resultProducts = cartList.stream().filter(line -> key.equals(line.getCategory())).collect(Collectors.toList());

		List<BigDecimal> bigList = new ArrayList<>();
		resultProducts.forEach(p -> bigList.add(p.getPrice().setScale(2, RoundingMode.HALF_UP)));

		BigDecimal sumCategory = bigList.stream().reduce(BigDecimal.ZERO, BigDecimal::add); // işlem yapılacak kategoriye ait toplam fiyat
		return sumCategory;
	}

	private void applyRemove(Map<Category, Integer> val, Map<Category, Integer> returnMap) {
		for (Entry<Category, Integer> item : returnMap.entrySet()) {
			val.remove(item.getKey());
			break;
		}
	}

	public void applyCoupon(double coupon) {
		int result = Double.compare(Double.valueOf(getTotalAmount()), Double.valueOf(coupon));
		if (result == 1) {
			discountPercentCoupon(0.1);
		}
	}

	private void discountPercentCoupon(double percent) {
		BigDecimal realTotalAmount = new BigDecimal(getTotalAmount()).setScale(2, RoundingMode.HALF_UP);
		BigDecimal discountAmount = realTotalAmount.multiply(new BigDecimal(percent)).setScale(2, RoundingMode.HALF_UP);
		totalCouponDicountVal = totalCouponDicountVal.add(discountAmount);
	}

}
