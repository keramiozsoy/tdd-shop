package com.app.tddshop.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.tddshop.domain.Campaign;
import com.app.tddshop.domain.Category;
import com.app.tddshop.domain.Product;
import com.app.tddshop.product.dao.ProductDAO;
import com.app.tddshop.product.services.impl.ProductBusinessServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductBusinessServiceTestUnit {

	@Mock
	private ProductBusinessServiceImpl productBusinessService;

	@Mock
	private ProductDAO productDAO;

	private List<Product> productList;
	private Campaign campaign = null;
	private Category category = null;
	private Product product = null;

	@Before
	public void initTest() {
		MockitoAnnotations.initMocks(this);

		campaign = new Campaign();
		campaign.setId(1L);
		campaign.setTitle("Kampanya 1");
		campaign.setDiscountAmount(new BigDecimal("10.0"));

		category = new Category();
		category.setId(1L);
		category.setTitle("testCategoryTitle");

		product = new Product();
		product.setId(1L);
		product.setTitle("testProductTitle");
		product.setPrice(new BigDecimal("115.37"));
		product.setCategory(category);

		productList = new ArrayList<>();
		productList.add(product);

	}

	@Test
	public void t1_service_when_findAll_then_return_findAll() {

		// Mocking -- karşılaşmak istediğim durumu belirledim.
		Mockito.when(productBusinessService.findAll()).thenReturn(productList); // Mockito

		// when -- Beklenen durumu oluşturdum.
		List<Product> resultList = productBusinessService.findAll();
		
		// then
		// Beklenen duruma gerçekten ulaşabiliyor muyum kontrol ettim.
		Mockito.verify(productBusinessService, Mockito.times(1)).findAll(); // findAll servisinin kesinlikle bir kez
																			// çağrılmasını istiyorum
		

		Mockito.verifyNoMoreInteractions(productBusinessService); // bu servisten başka istek yapılmamış olmasını
																	// bekliyorum
		Assert.assertThat(resultList, CoreMatchers.hasItem(productList.get(0))); // Hamcrest
																					// test olarak ürettiğim verideki
																					// elemanı mock ladığım sonuç
																					// listem ile karşlaştırıyorum

		Assertions.assertThat(productList.get(0).getTitle()) // AssertJ
				.isEqualTo("testProductTitle").startsWith("test").endsWith("ProductTitle");

	}

	@Test
	public void t2_service_when_findById_then_return_product() {
		Mockito.when(productBusinessService.findById(Mockito.anyLong())).thenReturn(product);
		
		Product p = productBusinessService.findById(1L);
		
		Mockito.verify(productBusinessService, Mockito.times(1)).findById(Mockito.anyLong());
		
		Assert.assertNotNull(p);
	}
	
	@Test
	public void t3_service_when_save_then_return_product() {
		Mockito.when(productBusinessService.save(Mockito.any())).thenReturn(product);
		
		Product p = productBusinessService.save(product);
		
		Mockito.verify(productBusinessService, Mockito.times(1)).save(Mockito.any());
		
		Assert.assertThat(p, CoreMatchers.is(product));
	}
	
	

}
