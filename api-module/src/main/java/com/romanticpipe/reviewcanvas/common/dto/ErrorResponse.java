package com.romanticpipe.reviewcanvas.common.dto;

import java.util.List;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.romanticpipe.reviewcanvas.exception.ErrorCode;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "성공 Response")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

	@Schema(description = "성공 여부", defaultValue = "false")
	private final boolean success = false;
	private final ErrorInfo data;

	public static ErrorResponse of(ErrorCode errorCode) {
		return new ErrorResponse(new ErrorInfo(errorCode.getCode(), errorCode.getMessage(), null));
	}

	public static ErrorResponse of(ErrorCode errorCode, List<ValidationError> validationErrorList) {
		return new ErrorResponse(new ErrorInfo(errorCode.getCode(), errorCode.getMessage(), validationErrorList));
	}

	public record ValidationError(String field, String message) {

		public static ValidationError of(final FieldError fieldError) {
			return new ValidationError(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

	private record ErrorInfo(String code,
							 String message,
							 @JsonInclude(JsonInclude.Include.NON_EMPTY)
							 List<ValidationError> errors) {
	}
}
