package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductReader {

	private final ProductRepository productRepository;

	public Optional<Product> findProduct(String mallId, Long productNo) {
		return productRepository.findByMallIdAndProductNo(mallId, productNo);
	}

	public List<Product> findProducts(Integer shopAdminId) {
		return productRepository.findAllByShopAdminId(shopAdminId);
	}
}
