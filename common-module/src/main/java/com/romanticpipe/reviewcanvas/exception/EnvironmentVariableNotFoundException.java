package com.romanticpipe.reviewcanvas.exception;

public class EnvironmentVariableNotFoundException extends BusinessException {
	public EnvironmentVariableNotFoundException(String key) {
		super(CommonErrorCode.INTERNAL_SERVER_ERROR, "Environment variable not found: " + key);
	}
}
