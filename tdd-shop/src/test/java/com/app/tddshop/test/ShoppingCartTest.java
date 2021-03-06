package com.app.tddshop.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.tddshop.TddShopApplication;
import com.app.tddshop.domain.Category;
import com.app.tddshop.domain.Product;
import com.app.tddshop.shoppingcart.DeliveryCostCalculator;
import com.app.tddshop.shoppingcart.ShoppingCart;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING) // isim sırasına göre metotları çalıştır
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartTest {

	private ShoppingCart shoppingCart = null;
	private Product product, product2, product3 = null;
	private Category category, category2, category3 = null;
	private BigDecimal zeroPrice, productPrice, productPrice2, productPrice3 = null;
	private static final double COUPON = 100.00; 
	private List<Product> cartList = null;

	@Before
	public void initTest() {
		shoppingCart = new ShoppingCart();
		product = new Product();
		product2 = new Product();
		product3 = new Product();
		zeroPrice = new BigDecimal("0.0");
		productPrice = new BigDecimal("115.37");
		productPrice2 = new BigDecimal("70.10");
		productPrice3 = new BigDecimal("20.941");
		category = new Category();
		category2 = new Category();
		category3 = new Category();
		cartList = shoppingCart.getCartList();
	}

	@Test
	public void t0_contextLoads() {
		TddShopApplication.main(new String[] {});
	}

	/** Sepette ürün yok ise büyüklüğü sıfır olmalıdır. */
	@Test
	public void t1_zero_size_list() {
		Assert.assertEquals("Listenin büyüklüğü sıfır değildir.", 0, shoppingCart.size());
	}

	/** Sepette bir ürün var ise boyutu bir olmalıdır. */
	@Test
	public void t2_list_size_after_adding_one_product() {
		product.setCategory(category);
		product.setPrice(productPrice);
		shoppingCart.add(product, 1);
		Assert.assertEquals("Listenin büyüklüğü bir değildir.", 1, shoppingCart.size());
	}

	/** Sepette iki ürün var ise boyutu iki olmalıdır. */
	@Test
	public void t3_list_size_after_adding_two_product() {
		product.setCategory(category);
		product2.setCategory(category2);
		product.setPrice(productPrice);
		product2.setPrice(productPrice2);
		shoppingCart.add(product, 1);
		shoppingCart.add(product2, 1);
		Assert.assertEquals("Listenin büyüklüğü iki değildir.", 2, shoppingCart.size());
	}

	/** Sepet bilgisini tutan listede ürün yok ise boyutu sıfır olmalıdır. */
	@Test
	public void t4_cart_list_return_nothing() {
		Assert.assertEquals("Liste boş değil", 0, cartList.size());
	}

	/**
	 * Sepete bir ürün eklenmiş ise sepet bilgisini tutan liste boyutu bir
	 * olmalıdır.
	 */
	@Test
	public void t5_cart_list_after_adding_one_product() {
		product.setCategory(category);
		product.setPrice(productPrice);
		shoppingCart.add(product, 1);
		Assert.assertEquals("Liste bir eleman dönmedi", 1, cartList.size());
	}

	/**
	 * Sepete iki ürün eklenmiş ise sepet bilgisini tutan listenin boyutu iki
	 * olmalıdır.
	 */
	@Test
	public void t6_cart_list_after_adding_two_product() {
		product.setCategory(category);
		product2.setCategory(category2);
		product.setPrice(productPrice);
		product2.setPrice(productPrice2);
		shoppingCart.add(product, 1);
		shoppingCart.add(product2, 1);
		Assert.assertEquals("Liste iki eleman dönmedi", 2, cartList.size());
	}

	/** Sepette ürün yok ise sepetin toplam fiyat tutarı sıfır olmalıdır. */
	@Test
	public void t7_shopping_cart_return_total_amount() {
		Assert.assertEquals("Toplam tutar sıfır dan farklıdır", 0.0d, shoppingCart.getTotalAmount(), 0);
	}

	/**
	 * Sepete bir ürün eklenmiş ise sepetin toplam fiyat tutarı eklenen ürünün fiyat
	 * bilgisi olmalıdır.
	 */
	@Test
	public void t8_when_adding_one_product_then_return_total_amount() {
		product.setCategory(category);
		product.setPrice(productPrice);
		shoppingCart.add(product, 1);
		Assert.assertEquals("Toplam tutar ürün fiyatından farklıdır.", product.getPrice(),
				BigDecimal.valueOf(shoppingCart.getTotalAmount()));
	}

	/**
	 * Sepete eklenen ürünlerin fiyatları toplamı, sepetin toplam fiyat tutarı
	 * alanına eşit olmalıdır.
	 */
	@Test
	public void t9_when_adding_two_product_then_return_total_amount() {
		product.setCategory(category);
		product2.setCategory(category2);
		product.setPrice(productPrice);
		product2.setPrice(productPrice2);

		BigDecimal expectedSum = product.getPrice().add(product2.getPrice());

		shoppingCart.add(product, 1);
		shoppingCart.add(product2, 1);

		Assert.assertEquals("Toplam tutar iki ürünün toplam fiyatından farklıdır", expectedSum,
				BigDecimal.valueOf(shoppingCart.getTotalAmount()));
	}

	/** Ürün bilgisi yok ise sepete eklenemez */
	@Test
	public void t10_when_product_is_null_should_not_add_shopping_cart() {
		Assert.assertFalse("Ürün bilgisi yok ise sepete ekleme işlemi yapılamaz",
				shoppingCart.checkRequiredPriceInfoForAddingProduct(null));
	}

	/** Ürün fiyatı yok ise sepete eklenemez */
	@Test
	public void t11_when_product_has_not_price_should_not_add_shopping_cart() {
		Assert.assertFalse("Sepete fiyat bilgisi olmayan ürün eklenemez", shoppingCart.checkRequiredPriceInfoForAddingProduct(product));
	}

	/**
	 * Ürün fiyatı yok ve ürün sepete eklenmeye çalışılmış ise sepet boş olmalıdır.
	 */
	@Test
	public void t12_when_product_has_not_price_should_empty_shopping_cart() {
		shoppingCart.add(product, 1);
		Assert.assertNotEquals("Fiyatı olmayan ürün sepete eklenemez", Boolean.TRUE,
				shoppingCart.checkRequiredPriceInfoForAddingProduct(product));
	}

	/** Kategori kategori yok ise sepete eklenemez */
	@Test
	public void t13_when_product_category_null_should_not_add_shopping_cart() {
		Assert.assertFalse("Kategori bilgisi yok ise sepete ekleme işlemi yapılamaz",
				shoppingCart.checkRequiredCategoryInfoForAddingProduct(null));
	}

	/** Ürün kategori yok ise sepete eklenemez */
	@Test
	public void t14_when_product_has_not_category_should_not_add_shopping_cart() {
		Assert.assertFalse("Sepete kategori bilgisi olmayan ürün eklenemez",
				shoppingCart.checkRequiredCategoryInfoForAddingProduct(product));
	}

	/**
	 * Kategori yok ve ürün sepete eklenmeye çalışılmış ise sepet boş olmalıdır.
	 */
	@Test
	public void t15_when_product_has_not_category_should_empty_shopping_cart() {
		shoppingCart.add(product, 1);
		Assert.assertNotEquals("Kategorisi olmayan ürün sepete eklenemez", Boolean.TRUE,
				shoppingCart.checkRequiredCategoryInfoForAddingProduct(product));
	}

	/**
	 * Aynı ürün iki kez eklenirse aynı kategoride iki eleman olduğundan frekansı 2
	 * olmalıdır.
	 */
	@Test
	public void t16_when_adding_two_same_product_should_frequency_two() {
		product.setCategory(category);
		product.setPrice(productPrice);
		shoppingCart.add(product, 1);
		shoppingCart.add(product, 1);

		Map<Category, Integer> resultFrequencies = shoppingCart.countFrequencies(cartList);

		boolean result = shoppingCart.hasSameCategoryInFrequencies(resultFrequencies);

		Assert.assertTrue("Sepette aynı kategoriye sahip  ürün yoktur.", result);
	}

	/**
	 * Farklı kategorideki ürünler bir kez eklenirse aynı kategoride iki eleman
	 * oldmadığından frekansı 2 den küçük olmalıdır.
	 */
	@Test
	public void t17_when_adding_two_different_cateogry_product_should_frequency_less_than_two() {
		product.setCategory(category);
		product.setPrice(productPrice);
		product2.setCategory(category2);
		product2.setPrice(productPrice2);
		shoppingCart.add(product, 1);
		shoppingCart.add(product2, 1);

		Map<Category, Integer> resultFrequencies = shoppingCart.countFrequencies(cartList);

		boolean result = shoppingCart.hasSameCategoryInFrequencies(resultFrequencies);

		Assert.assertFalse("Sepette aynı kategoriye sahip ürün vardır", result);
	}

	/**
	 * Sepete eklenen ürün yok ise sepet toplam tutarına indirim yapılmaz.
	 */
	@Test
	public void t18_when_product_is_null_total_should_zero_total_amount_after_discount() {
		Assert.assertEquals("Sepetin indirimli tutarı sıfırdan farklıdır.", zeroPrice.doubleValue(),
				shoppingCart.getTotalAmountAfterDiscount(), 0);
	}

	/**
	 * Sepete bir ürün eklendiğinde sepet toplam tutarına indirim yapılmaz.
	 */
	@Test
	public void t19_when_adding_one_product_should_zero_total_amount_after_discount() {
		product.setPrice(productPrice);
		shoppingCart.add(product, 1);
		Assert.assertEquals("Sepetin indirimli tutarı sıfırdan farklıdır.", zeroPrice.doubleValue(),
				shoppingCart.getTotalAmountAfterDiscount(), 0);
	}

	/**
	 * Sepete bir kategoride 3 ten fazla eklenmiş ise %20 indirim uygulanacak liste
	 * döner
	 */
	@Test
	public void t20_when_adding_greater_than_three_prouduct_should_return_twenty_percent_campaign_dicount() {
		product.setPrice(productPrice);
		product.setCategory(category);
		shoppingCart.add(product, 4);

		product2.setPrice(productPrice2);
		product2.setCategory(category2);
		shoppingCart.add(product2, 1);

		Map<Category, Integer> resultFrequenciesForGreaterThanThree = shoppingCart.countFrequencies(cartList);

		Map<Category, Integer> resultOnlyFrequenciesForGreaterThanThree = shoppingCart
				.hasCategoryCountGreaterThanExpectedCountInFrequencies(resultFrequenciesForGreaterThanThree, 3);

		Assert.assertFalse("Bir kategoride 3 ten fazla ürün bulunamadığından %20 indirim kampanyası uygulanamadı",
				resultOnlyFrequenciesForGreaterThanThree.isEmpty());

	}

	/**
	 * Sepete bir kategoride 5 ten fazla ürün eklenmiş ise %50 indirim uygulanacak
	 * liste döner
	 */
	@Test
	public void t21_when_adding_greater_than_five_prouduct_should_return_fifty_percent_campaign_dicount() {
		product.setPrice(productPrice);
		product.setCategory(category);
		shoppingCart.add(product, 6);

		product2.setPrice(productPrice2);
		product2.setCategory(category2);
		shoppingCart.add(product2, 10);

		product3.setPrice(productPrice3);
		product3.setCategory(category3);
		shoppingCart.add(product3, 2);

		Map<Category, Integer> resultFrequenciesForGreaterThanFive = shoppingCart.countFrequencies(cartList);

		Map<Category, Integer> resultOnlyFrequenciesForGreaterThanFive = shoppingCart
				.hasCategoryCountGreaterThanExpectedCountInFrequencies(resultFrequenciesForGreaterThanFive, 5);

		Assert.assertFalse("Bir kategoride 5 ten fazla ürün bulunamadığından %50 indirim kampanyası uygulanamadı",
				resultOnlyFrequenciesForGreaterThanFive.isEmpty());
	}

	/**
	 * Sepete bir kategoride 1 den fazla ürün eklenmiş ise 5 Tl indirim uygulanacak
	 * liste döner
	 */
	@Test
	public void t22_when_adding_greater_than_one_prouduct_should_return_five_lira_campaign_dicount() {
		product.setPrice(productPrice);
		product.setCategory(category);
		shoppingCart.add(product, 2);

		product2.setPrice(productPrice2);
		product2.setCategory(category2);
		shoppingCart.add(product2, 3);

		product3.setPrice(productPrice3);
		product3.setCategory(category3);
		shoppingCart.add(product3, 1);

		Map<Category, Integer> resultFrequenciesForGreaterThanOne = shoppingCart.countFrequencies(cartList);

		Map<Category, Integer> resultOnlyFrequenciesForGreaterThanOne = shoppingCart
				.hasCategoryCountGreaterThanExpectedCountInFrequencies(resultFrequenciesForGreaterThanOne, 1);

		Assert.assertFalse("Bir kategoride 1 ten fazla ürün bulunamadığından 5 TL indirim kampanyası uygulanamadı",
				resultOnlyFrequenciesForGreaterThanOne.isEmpty());
	}

	/**
	 * Sepete bir kategoride 5 ten fazla ürün eklenmiş ise %50 indirim uygulanacak
	 * Sepete bir kategoride 3 ten fazla eklenmiş ise %20 indirim uygulanacak Sepete
	 * bir kategoride 1 den fazla ürün eklenmiş ise 5 Tl indirim uygulanacak
	 */
	@Test
	public void t23_when_adding_greater_than_three_prouduct_should_return_twenty_percent_campaign_dicount() {
		product.setPrice(productPrice);
		product.setCategory(category);
		shoppingCart.add(product, 6);

		product2.setPrice(productPrice2);
		product2.setCategory(category2);
		shoppingCart.add(product2, 4);

		product3.setPrice(productPrice3);
		product3.setCategory(category3);
		shoppingCart.add(product3, 2);

		Map<Category, Integer> resultOrderedFrequenciesForApplyCampaignDiscount = shoppingCart.countFrequencies(cartList);

		shoppingCart.applyDiscount(resultOrderedFrequenciesForApplyCampaignDiscount);
		
		Assert.assertNotEquals("Indirim oluşmadığı için tutarı doğru hesaplanamadı", zeroPrice, shoppingCart.getTotalAmountAfterDiscount());

	}
	
	/**
	 * Total tutar kupon minimum tutarından fazla ise kupon uygulanır.
	 */
	@Test
	public void t24_when_total_amont_greater_than_coupon_amount_coupon_can_apply() {
		product.setPrice(productPrice);
		product.setCategory(category);
		shoppingCart.add(product, 6);

		product2.setPrice(productPrice2);
		product2.setCategory(category2);
		shoppingCart.add(product2, 4);

		product3.setPrice(productPrice3);
		product3.setCategory(category3);
		shoppingCart.add(product3, 2);

		Map<Category, Integer> resultOrderedFrequenciesForApplyCampaignDiscount = shoppingCart.countFrequencies(cartList);

		shoppingCart.applyDiscount(resultOrderedFrequenciesForApplyCampaignDiscount);
		
		shoppingCart.applyCoupon(COUPON);
		
		int result = Double.compare(Double.valueOf(shoppingCart.getTotalAmount()), Double.valueOf(COUPON));
		
		Assert.assertEquals("Toplam sepet tutarı kupon tutarıdan azdır.",1, result,0);
		
	}
	
	/**
	 * Total tutar kupon minimum tutarından fazla ise kupon uygulanmaz.
	 */
	@Test
	public void t25_when_total_amont_greater_than_coupon_amount_coupon_can_apply() {

		product3.setPrice(productPrice3);
		product3.setCategory(category3);
		shoppingCart.add(product3, 2);

		Map<Category, Integer> resultOrderedFrequenciesForApplyCampaignDiscount = shoppingCart.countFrequencies(cartList);

		shoppingCart.applyDiscount(resultOrderedFrequenciesForApplyCampaignDiscount);
		
		shoppingCart.applyCoupon(COUPON);
		
		int result = Double.compare(Double.valueOf(shoppingCart.getTotalAmount()), Double.valueOf(COUPON));
		
		Assert.assertNotEquals("Toplam sepet tutarı kupon tutarıdan fazladır.",1, result,0);
		
	}
	
	/**
	 * Total tutar kupon minimum tutarından az ise toplam kupon tutarı 0 olur.
	 */
	@Test
	public void t26_when_total_amont_greater_than_coupon_amount_coupon_can_apply() {

		product3.setPrice(productPrice3);
		product3.setCategory(category3);
		shoppingCart.add(product3, 2);

		Map<Category, Integer> resultOrderedFrequenciesForApplyCampaignDiscount = shoppingCart.countFrequencies(cartList);

		shoppingCart.applyDiscount(resultOrderedFrequenciesForApplyCampaignDiscount);
		
		shoppingCart.applyCoupon(COUPON);
		
		Assert.assertEquals("Total tutar kupon tutarı 0 ",zeroPrice, BigDecimal.valueOf(shoppingCart.getTotalCouponDiscount()) );
		
	}

	/**
	 * 2 farklı kategori 4 farklı ürün için maliyet
	 */
	@Test
	public void t27_cost_delivery() {
		product.setCategory(category);
		product.setPrice(productPrice);
		shoppingCart.add(product, 3);
		
		Product diffrentProductSameCategory = new  Product();
		diffrentProductSameCategory.setCategory(category);
		diffrentProductSameCategory.setPrice(productPrice);
		shoppingCart.add(diffrentProductSameCategory, 3);
		
		product2.setCategory(category2);
		product2.setPrice(productPrice2);
		shoppingCart.add(product2, 3);
		
		Product diffrentProductSameCategory2 = new  Product();
		diffrentProductSameCategory2.setCategory(category2);
		diffrentProductSameCategory2.setPrice(productPrice2);
		shoppingCart.add(diffrentProductSameCategory2, 3);
		
		
		
		BigDecimal costPerDelivery = new BigDecimal(BigInteger.ONE);
		BigDecimal costPerProduct = new BigDecimal(BigInteger.ONE);
		BigDecimal fixedCost = new BigDecimal("2.99");

		DeliveryCostCalculator cc = new DeliveryCostCalculator(costPerDelivery, costPerProduct, fixedCost);
		double result = cc.calculateFor(shoppingCart);
		
		
		Assert.assertNotEquals("Maliyet yanlış hesaplanmıştır.",fixedCost,result);
	}
	
	/**
	 * print metotu
	 */
	@Test
	public void t28_print() {
		product.setCategory(category);
		product.setPrice(productPrice);
		shoppingCart.add(product, 3);
		
		Product diffrentProductSameCategory = new  Product();
		diffrentProductSameCategory.setCategory(category);
		diffrentProductSameCategory.setPrice(productPrice);
		shoppingCart.add(diffrentProductSameCategory, 3);
		
		product2.setCategory(category2);
		product2.setPrice(productPrice2);
		shoppingCart.add(product2, 3);
		
		Product diffrentProductSameCategory2 = new  Product();
		diffrentProductSameCategory2.setCategory(category2);
		diffrentProductSameCategory2.setPrice(productPrice2);
		shoppingCart.add(diffrentProductSameCategory2, 3);
		
		shoppingCart.print();
	}

	
	
}
