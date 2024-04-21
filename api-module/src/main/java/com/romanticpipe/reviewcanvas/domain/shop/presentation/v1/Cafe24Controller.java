package com.romanticpipe.reviewcanvas.domain.shop.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.Cafe24UseCase;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.response.GetCafe24AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Cafe24Controller implements Cafe24Api {

	private final Cafe24UseCase cafe24UseCase;

	@Override
	@GetMapping("/cafe24/access-token")
	public ResponseEntity<SuccessResponse<GetCafe24AccessTokenResponse>> getCafe24AccessToken(
		@RequestParam(required = true) String mallId,
		@RequestParam(required = true) String authCode
	) {
		GetCafe24AccessTokenResponse response = cafe24UseCase.getCafe24AccessToken(mallId, authCode);
		return SuccessResponse.of(response).asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/cafe24/reissue-access-token")
	public ResponseEntity<SuccessResponse<GetCafe24AccessTokenResponse>> reissueCafe24AccessToken(
		@RequestParam(required = true) String mallId,
		@RequestParam(required = true) String refreshToken
	) {
		GetCafe24AccessTokenResponse response = cafe24UseCase.reissueCafe24AccessToken(mallId, refreshToken);
		return SuccessResponse.of(response).asHttp(HttpStatus.OK);
	}
}
