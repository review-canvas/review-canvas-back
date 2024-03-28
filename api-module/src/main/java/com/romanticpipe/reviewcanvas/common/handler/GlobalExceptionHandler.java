package com.romanticpipe.reviewcanvas.common.handler;

import com.romanticpipe.reviewcanvas.common.dto.ErrorResponse;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.CommonErrorCode;
import com.romanticpipe.reviewcanvas.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		log.error("BusinessException", ex);
		return handleExceptionInternal(ex.getErrorCode());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
		log.error("IllegalArgumentException", ex);
		return handleExceptionInternal(CommonErrorCode.INVALID_INPUT_VALUE);
	}

	// @Valid 예외처리
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																  HttpHeaders headers, HttpStatusCode status,
																  WebRequest request) {
		log.error("MethodArgumentNotValidException", ex);
		return handleExceptionInternal(ex, CommonErrorCode.INVALID_INPUT_VALUE);
	}


	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
																  HttpHeaders headers, HttpStatusCode status,
																  WebRequest request) {
		log.error("HttpMessageNotReadableException", ex);
		return handleExceptionInternal(CommonErrorCode.INVALID_INPUT_VALUE);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex) {
		log.error("Exception", ex);
		return handleExceptionInternal(CommonErrorCode.INTERNAL_SERVER_ERROR);
	}


	private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getStatus())
			.body(ErrorResponse.of(errorCode));
	}

	private ResponseEntity<Object> handleExceptionInternal(BindException bindException, ErrorCode errorCode) {
		List<ErrorResponse.ValidationError> validationErrorList = bindException.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(ErrorResponse.ValidationError::of)
			.collect(Collectors.toList());

		return ResponseEntity.status(errorCode.getStatus())
			.body(ErrorResponse.of(errorCode, validationErrorList));
	}

}
