package com.romanticpipe.reviewcanvas.exception;

public class ProductNotFoundException extends BusinessException {
	public ProductNotFoundException() {
		super(ReviewErrorCode.PRODUCT_NOT_FOUND);
	}
}
