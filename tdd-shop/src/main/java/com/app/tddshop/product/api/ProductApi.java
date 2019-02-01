package com.app.tddshop.product.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.tddshop.domain.Product;
import com.app.tddshop.product.service.ProductBusinessService;

@RequestMapping(value = "/productapi")
@ResponseBody
@Controller
public class ProductApi {

	@Autowired
	private ProductBusinessService businessService;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findAll() {
		List<Product> viewList = businessService.findAll();

		return new ResponseEntity<List<Product>>(viewList,
				viewList != null && viewList.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);

	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = businessService.findById(id);

		return new ResponseEntity<Product>(product, product != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);

	}

}
