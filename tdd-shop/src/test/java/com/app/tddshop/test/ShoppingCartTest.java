package com.app.tddshop.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.app.tddshop.domain.Product;
import com.app.tddshop.shoppingcart.ShoppingCart;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartTest {

	private ShoppingCart cart = null;
	private Product product = null;
	private Product product2 = null;

	@Before
	public void initTest() {
		cart = new ShoppingCart();
		product = new Product();
		product2 = new Product();
	}

	@Test
	public void t1_zero_size_list() {
		Assert.assertEquals("Listenin büyüklüğü sıfır değildir.", 0, cart.size());
	}

	@Test
	public void t2_list_size_after_adding_one_product() {
		cart.add(product);
		Assert.assertEquals("Listenin büyüklüğü bir değildir.", 1, cart.size());
	}

	@Test
	public void t3_list_size_after_adding_two_product() {
		cart.add(product);
		cart.add(product2);
		Assert.assertEquals("Listenin büyüklüğü iki değildir.", 2, cart.size());
	}
	
	@Test
	public void t4_cart_list_return_nothing() {
		List<Product> cartList = cart.getCartList();
		Assert.assertEquals("List boş değil",0,cartList.size());
	}
}
