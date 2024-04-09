package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.romanticpipe.reviewcanvas.cafe24.Cafe24AccessToken;
import com.romanticpipe.reviewcanvas.cafe24.Cafe24Client;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.response.GetCafe24AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShopUseCaseImpl implements ShopUseCase {

	private final Cafe24Client cafe24Client;

	@Override
	public GetCafe24AccessTokenResponse getCafe24AccessToken(String mallId, String authCode) {
		Cafe24AccessToken cafe24AccessToken = cafe24Client.getCafe24AccessToken(mallId, authCode);
		return GetCafe24AccessTokenResponse.from(cafe24AccessToken);
	}
}
