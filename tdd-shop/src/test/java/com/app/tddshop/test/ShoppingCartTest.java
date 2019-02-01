package com.app.tddshop.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.tddshop.domain.Product;
import com.app.tddshop.shoppingcart.ShoppingCart;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartTest {

	private ShoppingCart cart = null;
	private Product product = null;
	
	@Before
	public void initTest() {
		 cart = new ShoppingCart();
		 product = new Product();
	}
	
	@Test
	public void t1_zero_size_list() {
		Assert.assertEquals("Listenin büyüklüğü sıfır değildir.", 0, cart.size());
	}
	
	@Test
	public void t2_test_list_size_after_adding_one_product() {
		cart.add(product);
		Assert.assertEquals("Listenin büyüklüğü bir değildir.", 1, cart.size());
	}
}
