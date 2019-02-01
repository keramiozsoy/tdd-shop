package com.app.tddshop.test;

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

	@Test
	public void t1_zero_size_list() {
		Assert.assertEquals("Listenin büyüklüğü sıfır değildir.", 0, shoppingCart.size());
	}

	@Test
	public void t2_list_size_after_adding_one_product() {
		shoppingCart.add(product);
		Assert.assertEquals("Listenin büyüklüğü bir değildir.", 1, shoppingCart.size());
	}

	@Test
	public void t3_list_size_after_adding_two_product() {
		shoppingCart.add(product);
		shoppingCart.add(product2);
		Assert.assertEquals("Listenin büyüklüğü iki değildir.", 2, shoppingCart.size());
	}

	@Test
	public void t4_cart_list_return_nothing() {
		Assert.assertEquals("Liste boş değil", 0, cartList.size());
	}

	@Test
	public void t5_cart_list_after_adding_one_product() {
		shoppingCart.add(product);
		Assert.assertEquals("Liste bir eleman dönmedi", 1, cartList.size());
	}

	@Test
	public void t6_cart_list_after_adding_two_product() {
		shoppingCart.add(product);
		shoppingCart.add(product2);
		Assert.assertEquals("Liste iki eleman dönmedi", 2, cartList.size());
	}

}
