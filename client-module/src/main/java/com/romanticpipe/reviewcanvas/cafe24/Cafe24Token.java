package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAuthToken;

import java.time.LocalDateTime;

record Cafe24Token(String accessToken,
				   LocalDateTime accessTokenExpiresAt,
				   String refreshToken,
				   LocalDateTime refreshTokenExpiresAt) {

	public static Cafe24Token from(ShopAuthToken shopAuthToken) {
		return new Cafe24Token(shopAuthToken.getAccessToken(),
			shopAuthToken.getAccessTokenExpiresAt(),
			shopAuthToken.getRefreshToken(),
			shopAuthToken.getRefreshTokenExpiresAt());
	}
}
