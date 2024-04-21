package com.romanticpipe.reviewcanvas.cafe24.authentication;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Cafe24AccessToken(String accessToken,
								LocalDateTime expiresAt,
								String refreshToken,
								LocalDateTime refreshTokenExpiresAt,
								String clientId,
								String mallId,
								String userId,
								List<String> scopes,
								LocalDateTime issuedAt) {

	public ShopAuthToken toShopAuthToken() {
		String scope = scopes.stream().reduce((a, b) -> a + "," + b).orElse("");
		return ShopAuthToken.builder()
			.mallId(mallId)
			.accessToken(accessToken)
			.accessTokenExpiresAt(expiresAt)
			.refreshToken(refreshToken)
			.refreshTokenExpiresAt(refreshTokenExpiresAt)
			.scope(scope)
			.build();
	}
}
