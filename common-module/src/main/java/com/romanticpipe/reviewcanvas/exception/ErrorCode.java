package com.romanticpipe.reviewcanvas.exception;

public interface ErrorCode {
	int getStatus();

	String getCode();

	String getMessage();
}
