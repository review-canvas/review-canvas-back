package com.romanticpipe.reviewcanvas.domain.review.application.usecase.response;

import com.romanticpipe.reviewcanvas.domain.Product;

public record GetProductResponse(
	Long productId,
	Long productNo,
	String productName
) {
	public static GetProductResponse from(Product product) {
		return new GetProductResponse(product.getId(), product.getProductNo(), product.getName());
	}
}
