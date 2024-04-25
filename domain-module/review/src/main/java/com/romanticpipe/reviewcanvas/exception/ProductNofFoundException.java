package com.romanticpipe.reviewcanvas.exception;

public class ProductNofFoundException extends BusinessException {
	public ProductNofFoundException() {
		super(ReviewErrorCode.PRODUCT_NOT_FOUND);
	}
}
