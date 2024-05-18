package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class ReviewDesignWriteNotFoundException extends BusinessException {
	public ReviewDesignWriteNotFoundException() {
		super(ReviewPropertyErrorCode.REVIEW_DESIGN_WRITE_NOT_FOUND);
	}
}
