package com.romanticpipe.reviewcanvas.exception;

public class WebClientException extends BusinessException {
	public WebClientException(ErrorCode errorCode, String body) {
		super(errorCode, "http response body: " + body);
	}

}
