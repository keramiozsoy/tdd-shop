package com.app.tddshop.test;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.tddshop.domain.Category;
import com.app.tddshop.domain.Product;
import com.app.tddshop.shoppingcart.ShoppingCart;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING) // isim sırasına göre metotları çalıştır
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartTest {

	private ShoppingCart shoppingCart = null;
	private Product product, product2 = null;
	private Category category, category2 = null;
	private Double zeroPrice, productPrice, productPrice2 = null;
	private List<Product> cartList = null;

	@Before
	public void initTest() {
		shoppingCart = new ShoppingCart();
		product = new Product();
		product2 = new Product();
		zeroPrice = new Double("0.0");
		productPrice = new Double("5.5");
		productPrice2 = new Double("4.1");
		category = new Category();
		category2 = new Category();
		cartList = shoppingCart.getCartList();
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
		shoppingCart.add(product);
		Assert.assertEquals("Listenin büyüklüğü bir değildir.", 1, shoppingCart.size());
	}

	/** Sepette iki ürün var ise boyutu iki olmalıdır. */
	@Test
	public void t3_list_size_after_adding_two_product() {
		product.setCategory(category);
		product2.setCategory(category2);
		product.setPrice(productPrice);
		product2.setPrice(productPrice2);
		shoppingCart.add(product);
		shoppingCart.add(product2);
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
		shoppingCart.add(product);
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
		shoppingCart.add(product);
		shoppingCart.add(product2);
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
		shoppingCart.add(product);
		Assert.assertEquals("Toplam tutar ürün fiyatından farklıdır.", product.getPrice(), shoppingCart.getTotalAmount(), 0);
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

		Double expectedSum = product.getPrice() + product2.getPrice();

		shoppingCart.add(product);
		shoppingCart.add(product2);

		Assert.assertEquals("Toplam tutar iki ürünün toplam fiyatından farklıdır", expectedSum, shoppingCart.getTotalAmount(), 0);
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
		shoppingCart.add(product);
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
		shoppingCart.add(product);
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
		shoppingCart.add(product);
		shoppingCart.add(product);

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
		shoppingCart.add(product);
		shoppingCart.add(product2);

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
		shoppingCart.add(product);
		Assert.assertEquals("Sepetin indirimli tutarı sıfırdan farklıdır.", zeroPrice.doubleValue(),
				shoppingCart.getTotalAmountAfterDiscount(), 0);
	}
	
	
	/**
	 * Sepete bir kategoride 3 ten fazla eklenmiş ise  %20 indirim uygulanır
	 */
	@Test
	public void t20_when_adding_greater_than_three_prouduct_should_return_twenty_percent_dicount() {
		product.setPrice(productPrice);
		product.setCategory(category);
		shoppingCart.add(product);
		
		product = new Product();
		product.setPrice(productPrice);
		product.setCategory(category);
		shoppingCart.add(product);
		
		product = new Product();
		product.setPrice(productPrice);
		product.setCategory(category);
		shoppingCart.add(product);
		
		product = new Product();
		product.setPrice(productPrice);
		product.setCategory(category);
		shoppingCart.add(product);
		
		Map<Category, Integer> resultFrequenciesForGreaterThanThree = shoppingCart.countFrequencies(cartList);
		
		Map<Category, Integer> resultOnlyFrequenciesForGreaterThanThree = shoppingCart.hasCategoryCountGreaterThanThreeInFrequencies(resultFrequenciesForGreaterThanThree); 
		
		Assert.assertFalse("Bir kategoride 3 ten fazla ürün bulunamadığından %20 indirim kampanyası uygulanamadı", resultOnlyFrequenciesForGreaterThanThree.isEmpty());
		
	}


}
