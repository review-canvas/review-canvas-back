package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.response.GetCafe24AccessTokenResponse;

public interface Cafe24UseCase {
	GetCafe24AccessTokenResponse getCafe24AccessToken(String mallId, String authCode);

	/**
	 * 만약 refresh token이 유효하지 않거나 만료되었을 경우, INVALID_OR_EXPIRED_REFRESH_TOKEN error를 발생시킨다.
	 */
	GetCafe24AccessTokenResponse reissueCafe24AccessToken(String mallId, String refreshToken);
}
