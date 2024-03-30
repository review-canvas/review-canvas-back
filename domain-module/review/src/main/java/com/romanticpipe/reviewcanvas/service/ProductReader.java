package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductReader {

	private final ProductRepository productRepository;

	public Product findByProductId(String productId) {
		return productRepository.findByProductId(productId);
	}
}
