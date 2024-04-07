package com.romanticpipe.reviewcanvas.domain.shop.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.ShopUseCase;
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
public class ShopController implements ShopApi {

	private final ShopUseCase shopUseCase;

	@Override
	@GetMapping("/cafe24/access-token")
	public ResponseEntity<SuccessResponse<GetCafe24AccessTokenResponse>> getCafe24AccessToken(
		@RequestParam(required = true) String mallId,
		@RequestParam(required = true) String authCode
	) {
		var response = shopUseCase.getCafe24AccessToken(mallId, authCode);
		return SuccessResponse.of(response).asHttp(HttpStatus.OK);
	}
}
