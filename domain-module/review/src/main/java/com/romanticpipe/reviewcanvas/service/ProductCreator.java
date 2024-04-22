package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreator {

	private final ProductRepository productRepository;

	public Product save(Product product) {
		return productRepository.save(product);
	}
}
