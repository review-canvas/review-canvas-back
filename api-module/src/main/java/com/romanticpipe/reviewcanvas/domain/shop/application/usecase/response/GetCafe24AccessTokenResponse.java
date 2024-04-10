package com.romanticpipe.reviewcanvas.domain.shop.application.usecase.response;

import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AccessToken;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Schema(description = "카페24 액세스 토큰 조회 응답")
public record GetCafe24AccessTokenResponse(
	@Schema(description = "API 호출시 필요한 액세스 토큰", requiredMode = Schema.RequiredMode.REQUIRED)
	String accessToken,
	@Schema(description = "액세스 토큰 만료 일시/ 발급으로부터 2시간 후 만료", requiredMode = Schema.RequiredMode.REQUIRED)
	LocalDateTime expiresAt,
	@Schema(description = "액세스 토큰을 재발급 할 수 있는 토큰", requiredMode = Schema.RequiredMode.REQUIRED)
	String refreshToken,
	@Schema(description = "리프레시 토큰 만료 일시/ 발급으로부터 14일 후 만료", requiredMode = Schema.RequiredMode.REQUIRED)
	LocalDateTime refreshTokenExpiresAt,
	@Schema(description = "클라이언트 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
	String clientId,
	@Schema(description = "쇼핑몰 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
	String mallId,
	@Schema(description = "쇼핑몰 사용자(운영자) 아이디 [shop admin id]", requiredMode = Schema.RequiredMode.REQUIRED)
	String userId,
	@Schema(description = "사용 동의된 Scope 정보", requiredMode = Schema.RequiredMode.REQUIRED)
	List<String> scopes,
	@Schema(description = "액세스 토큰 발급 일시", requiredMode = Schema.RequiredMode.REQUIRED)
	LocalDateTime issuedAt) {

	public static GetCafe24AccessTokenResponse from(Cafe24AccessToken cafe24AccessToken) {
		return GetCafe24AccessTokenResponse.builder()
			.accessToken(cafe24AccessToken.accessToken())
			.expiresAt(cafe24AccessToken.expiresAt())
			.refreshToken(cafe24AccessToken.refreshToken())
			.refreshTokenExpiresAt(cafe24AccessToken.refreshTokenExpiresAt())
			.clientId(cafe24AccessToken.clientId())
			.mallId(cafe24AccessToken.mallId())
			.userId(cafe24AccessToken.userId())
			.scopes(cafe24AccessToken.scopes())
			.issuedAt(cafe24AccessToken.issuedAt())
			.build();
	}
}
