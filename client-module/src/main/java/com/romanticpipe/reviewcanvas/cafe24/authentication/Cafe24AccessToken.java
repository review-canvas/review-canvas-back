package com.romanticpipe.reviewcanvas.cafe24.authentication;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;
import org.springframework.util.StringUtils;

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
		String scope = String.join(",", scopes);
		return ShopAuthToken.builder()
			.mallId(mallId)
			.accessToken(accessToken)
			.accessTokenExpiresAt(expiresAt)
			.refreshToken(refreshToken)
			.refreshTokenExpiresAt(refreshTokenExpiresAt)
			.scope(scope)
			.build();
	}

	public boolean validateContent() {
		return !StringUtils.hasText(accessToken) || expiresAt == null || !StringUtils.hasText(refreshToken)
			|| refreshTokenExpiresAt == null || !StringUtils.hasText(mallId);
	}
}
