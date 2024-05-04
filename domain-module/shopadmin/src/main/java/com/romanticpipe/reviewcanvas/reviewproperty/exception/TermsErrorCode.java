package com.romanticpipe.reviewcanvas.reviewproperty.exception;

import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TermsErrorCode implements ErrorCode {

	TERMS_ERROR_CODE(400, "T001", "약관 태그를 찾을 수 없습니다."),
	NOT_CONSENT_MANDATORY_TERMS(400, "T002", "필수 약관에 동의하지 않았습니다.");

	private final int status;
	private final String code;
	private final String message;

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
