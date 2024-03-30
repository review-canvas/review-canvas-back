package com.romanticpipe.reviewcanvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	Product findByProductId(String productId);
}
