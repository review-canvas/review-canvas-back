package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.response.GetCafe24AccessTokenResponse;

public interface Cafe24UseCase {
	GetCafe24AccessTokenResponse getCafe24AccessToken(String mallId, String authCode);
}
