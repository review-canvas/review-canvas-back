package com.romanticpipe.reviewcanvas.service;

import java.util.List;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ProductErrorCode;
import com.romanticpipe.reviewcanvas.exception.ProductNofFoundException;
import com.romanticpipe.reviewcanvas.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductValidator {

	private final ProductRepository productRepository;

	public Product validByProductId(String productId) {
		return productRepository.findById(productId)
			.orElseThrow(() -> new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND));
	}

	public Product validateByMallIdAndProductNo(String mallId, Long productNo) {
		return productRepository.findByMallIdAndProductNo(mallId, productNo)
			.orElseThrow(ProductNofFoundException::new);
	}
}
