package com.romanticpipe.reviewcanvas.cafe24.product;

import com.romanticpipe.reviewcanvas.cafe24.Cafe24ErrorCode;
import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class Cafe24ProductNotFoundException extends BusinessException {
	public Cafe24ProductNotFoundException() {
		super(Cafe24ErrorCode.PRODUCT_NOT_FOUND);
	}
}
