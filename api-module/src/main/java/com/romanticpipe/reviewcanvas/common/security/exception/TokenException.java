package com.romanticpipe.reviewcanvas.common.security.exception;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TokenException extends BusinessException {

	public TokenException(ErrorCode errorCode) {
		super(errorCode);
	}

	public TokenException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode, throwable);
	}

	public TokenException(ErrorCode errorCode, String message) {
		super(errorCode, message);
	}
}
