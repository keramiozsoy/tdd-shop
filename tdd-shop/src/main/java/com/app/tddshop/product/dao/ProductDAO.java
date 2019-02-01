package com.app.tddshop.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.tddshop.domain.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {

}