package com.app.tddshop;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.tddshop.domain.Product;
import com.app.tddshop.domain.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TddShopApplicationTests {

	@Test
	public void contextLoads() {
		TddShopApplication.main(new String[] {});
	}

	@Before
	public void initTest() {
		MockitoAnnotations.initMocks(this);

		Product p = new Product();
		p.setId(1L);
		p.setTitle("testProductTitle");
		p.setPrice(new BigDecimal("115.37"));


		Category c = new Category();
		c.setId(1L);
		c.setTitle("testCategory");
	}

}
