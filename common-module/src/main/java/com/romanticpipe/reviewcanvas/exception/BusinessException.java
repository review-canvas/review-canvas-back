package com.romanticpipe.reviewcanvas.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class BusinessException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1905122041950251207L;
	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode.getMessage(), throwable);
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
}
