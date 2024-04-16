package com.romanticpipe.reviewcanvas.common.security.exception;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {

	private final ErrorCode errorCode;

	public TokenException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public TokenException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode.getMessage(), throwable);
		this.errorCode = errorCode;
	}

	public TokenException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}
