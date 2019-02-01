package com.app.tddshop;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.app.tddshop.test.ProductBusinessServiceTestUnit;
import com.app.tddshop.test.ShoppingCartTest;

@RunWith(Suite.class)
@SuiteClasses({ ProductBusinessServiceTestUnit.class,ShoppingCartTest.class })
public class TddShopApplicationTests {

}

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TddShopApplicationTests {
//
//	@Test
//	public void contextLoads() {
//		TddShopApplication.main(new String[] {});
//	}
//
//	@Before
//	public void initTest() {
//		MockitoAnnotations.initMocks(this);
//		
//		
//		Campaign campaign = new Campaign(); 
//		campaign.setId(1L);
//		campaign.setTitle("Kampanya 1");
//		campaign.setDiscountAmount(new BigDecimal("10.0"));
//		
//		Category category = new Category();
//		category.setId(1L);
//		category.setTitle("testCategoryTitle");
//		category.setCampaing(campaign);
//		
//		Product p = new Product();
//		p.setId(1L);
//		p.setTitle("testProductTitle");
//		p.setPrice(new BigDecimal("115.37"));
//		p.setCategory(category);
//		
//	}
//
//}
