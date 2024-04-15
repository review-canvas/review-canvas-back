package com.romanticpipe.reviewcanvas.domain.shop.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.response.GetCafe24AccessTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "shop", description = "쇼핑몰 API")
public interface ShopApi {

	@Operation(summary = "cafe24 액세스 토큰 발급", description = "auth code로 cafe24 액세스 토큰을 발급한다. "
		+ "https://developers.cafe24.com/app/front/app/develop/oauth/token")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 액세스 토큰을 발급했습니다."),
		@ApiResponse(
			responseCode = "400",
			description = "C003: 외부 API 호출 중 오류가 발생했습니다.",
			content = @Content(schema = @Schema(hidden = true))),
	})
	@GetMapping("/cafe24/access-token")
	ResponseEntity<SuccessResponse<GetCafe24AccessTokenResponse>> getCafe24AccessToken(
		@Schema(name = "mall id", description = "쇼핑몰의 아이디") @RequestParam(required = true) String mallId,
		@Schema(name = "authorization code", description = "인증 코드") @RequestParam(required = true) String authCode
	);

	@Operation(summary = "cafe24 액세스 토큰 재발급", description = "refresh token으로 cafe24 access token을 재발급한다. "
		+ "[주의]: 해당 api를 호출하면 refresh token도 항상 재발급됩니다. "
		+ "https://developers.cafe24.com/app/front/app/develop/oauth/retoken")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 액세스 토큰을 재발급했습니다."),
		@ApiResponse(
			responseCode = "400",
			description = "C003: 외부 API 호출 중 오류가 발생했습니다.",
			content = @Content(schema = @Schema(hidden = true))),
	})
	@GetMapping("/cafe24/reissue-access-token")
	ResponseEntity<SuccessResponse<GetCafe24AccessTokenResponse>> reissueCafe24AccessToken(
		@Schema(name = "mall id", description = "쇼핑몰의 아이디") @RequestParam(required = true) String mallId,
		@Schema(name = "refresh token", description = "cafe24 refresh token")
		@RequestParam(required = true) String refreshToken
	);
}