package com.app.tddshop.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.tddshop.shoppingcart.ShoppingCart;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartTest {

	@Test
	public void t1_zero_size_list() {
		ShoppingCart cart = new ShoppingCart();
		Assert.assertEquals("Listenin büyüklüğü sıfır değildir.", 0, cart.size());
	}
	
}
