package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;

public class TermsNotFoundException extends BusinessException {
	public TermsNotFoundException() {
		super(TermsErrorCode.TERMS_ERROR_CODE);
	}
}
