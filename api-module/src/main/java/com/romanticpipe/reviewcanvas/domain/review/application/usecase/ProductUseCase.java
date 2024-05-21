package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.Product;

public interface ProductUseCase {
	Product createProduct(String mallId, Long productNo);
}
