package com.app.tddshop.product.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.tddshop.domain.Product;
import com.app.tddshop.product.dao.ProductDAO;
import com.app.tddshop.product.service.ProductBusinessService;


@Service
public class ProductBusinessServiceImpl implements ProductBusinessService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public List<Product> findAll() {
		List<Product> resultList =  (List<Product>)productDAO.findAll();
		return resultList;
	}

	@Override
	public Product save(Product product) {
		Product result = productDAO.save(product);
		return result;
	}

	@Override
	public Product findById(Long id) {
		Optional<Product> result = productDAO.findById(id);

		if (!result.isPresent())
			return null;
		
		return result.get();
	}

}