package com.app.tddshop.product.service;

import java.util.List;

import com.app.tddshop.domain.Product;

public interface ProductBusinessService {

	public List<Product> findAll();

	public Product save(Product product);

	public Product findById(Long id);

}
