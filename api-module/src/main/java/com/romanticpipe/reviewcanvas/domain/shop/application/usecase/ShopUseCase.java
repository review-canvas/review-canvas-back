package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.response.GetCafe24AccessTokenResponse;

public interface ShopUseCase {
	GetCafe24AccessTokenResponse getCafe24AccessToken(String mallId, String authCode);

	GetCafe24AccessTokenResponse reissueCafe24AccessToken(String mallId, String refreshToken);
}
