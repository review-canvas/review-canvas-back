package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class ReviewLayoutNotFoundException extends BusinessException {

	public ReviewLayoutNotFoundException() {
		super(ReviewPropertyErrorCode.REVIEW_LAYOUT_NOT_FOUND);
	}
}
