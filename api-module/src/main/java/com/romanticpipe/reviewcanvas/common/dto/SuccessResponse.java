package com.romanticpipe.reviewcanvas.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Getter
@Schema(description = "성공 Response")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> {

	@Schema(description = "성공 여부", defaultValue = "true")
	private final boolean success = true;

	@JsonInclude(Include.NON_NULL)
	private T data;

	public static <T> SuccessResponse<T> of(T data) {
		SuccessResponse<T> successResponse = new SuccessResponse<>();

		successResponse.data = data;

		return successResponse;
	}

	public static SuccessResponse<Void> ofNoData() {
		return new SuccessResponse<>();
	}

	public ResponseEntity<SuccessResponse<T>> asHttp(HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus).body(this);
	}

	public ResponseEntity<SuccessResponse<T>> okWithCookie(ResponseCookie responseCookie) {
		return ResponseEntity.ok()
			.header(SET_COOKIE, responseCookie.toString())
			.body(this);
	}
}
