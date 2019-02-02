package com.app.tddshop.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.tddshop.domain.Product;
import com.app.tddshop.shoppingcart.ShoppingCart;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING) // isim sırasına göre metotları çalıştır
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartTest {

	private ShoppingCart shoppingCart = null;
	private Product product = null;
	private Product product2 = null;
	private List<Product> cartList = null;

	@Before
	public void initTest() {
		shoppingCart = new ShoppingCart();
		product = new Product();
		product2 = new Product();
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
		shoppingCart.add(product);
		Assert.assertEquals("Listenin büyüklüğü bir değildir.", 1, shoppingCart.size());
	}

	/** Sepette iki ürün var ise boyutu iki olmalıdır. */
	@Test
	public void t3_list_size_after_adding_two_product() {
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
		shoppingCart.add(product);
		Assert.assertEquals("Liste bir eleman dönmedi", 1, cartList.size());
	}

	/**
	 * Sepete iki ürün eklenmiş ise sepet bilgisini tutan listenin boyutu iki
	 * olmalıdır.
	 */
	@Test
	public void t6_cart_list_after_adding_two_product() {
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
		product.setPrice(new Double(5.5));
		shoppingCart.add(product);
		Assert.assertEquals("Toplam tutar ürün fiyatından farklıdır.", product.getPrice(),
				shoppingCart.getTotalAmount(), 0);
	}

	/**
	 * Sepete eklenen ürünlerin fiyatları toplamı, sepetin toplam fiyat tutarı
	 * alanına eşit olmalıdır.
	 */
	@Test
	public void t9_when_adding_two_product_then_return_total_amount() {
		product.setPrice(new Double(5.5));
		product2.setPrice(new Double(4.1));

		Double expectedSum = product.getPrice() + product2.getPrice();

		shoppingCart.add(product);
		shoppingCart.add(product2);

		Assert.assertEquals("Toplam tutar iki ürünün toplam fiyatından farklıdır", expectedSum,
				shoppingCart.getTotalAmount(), 0);
	}

}
