package com.romanticpipe.reviewcanvas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ProductErrorCode;
import com.romanticpipe.reviewcanvas.repository.ProductRepository;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductValidator {

	private final ProductRepository productRepository;

	public Product validByProductId(String productId){
		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isEmpty())
			throw new BusinessException(ProductErrorCode.PRODUCT_NOT_FOUND);
		return productOptional.get();
	}

}
