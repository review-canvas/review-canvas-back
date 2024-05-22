package com.romanticpipe.reviewcanvas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findProduct(String mallId, Long productNo) {
		return productRepository.findByMallIdAndProductNo(mallId, productNo);
	}

	public Optional<Product> findProduct(Long productId) {
		return productRepository.findById(productId);
	}

	public List<Product> findProducts(Integer shopAdminId) {
		return productRepository.findAllByShopAdminId(shopAdminId);
	}

}
