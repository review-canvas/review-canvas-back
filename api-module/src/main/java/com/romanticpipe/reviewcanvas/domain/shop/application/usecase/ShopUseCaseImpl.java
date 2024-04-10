package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AccessToken;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AccessTokenRequest;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AuthenticationClient;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.response.GetCafe24AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShopUseCaseImpl implements ShopUseCase {

	private final Cafe24AuthenticationClient cafe24AuthenticationClient;

	@Override
	public GetCafe24AccessTokenResponse getCafe24AccessToken(String mallId, String authCode) {
		Cafe24AccessTokenRequest requestBody = Cafe24AccessTokenRequest.of(authCode);
		Cafe24AccessToken cafe24AccessToken = cafe24AuthenticationClient.getAccessToken(mallId, requestBody);
		return GetCafe24AccessTokenResponse.from(cafe24AccessToken);
	}
}
