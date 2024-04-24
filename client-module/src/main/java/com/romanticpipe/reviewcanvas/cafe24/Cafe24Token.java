package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;

import java.time.LocalDateTime;
import java.util.Objects;

record Cafe24Token(String accessToken,
				   LocalDateTime accessTokenExpiresAt,
				   String refreshToken,
				   LocalDateTime refreshTokenExpiresAt) {

	Cafe24Token {
		Objects.requireNonNull(accessToken);
		Objects.requireNonNull(accessTokenExpiresAt);
		Objects.requireNonNull(refreshToken);
		Objects.requireNonNull(refreshTokenExpiresAt);
	}

	public static Cafe24Token from(ShopAuthToken shopAuthToken) {
		return new Cafe24Token(shopAuthToken.getAccessToken(),
			shopAuthToken.getAccessTokenExpiresAt(),
			shopAuthToken.getRefreshToken(),
			shopAuthToken.getRefreshTokenExpiresAt());
	}
}
