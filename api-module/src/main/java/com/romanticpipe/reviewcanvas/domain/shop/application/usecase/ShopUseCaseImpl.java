package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.romanticpipe.reviewcanvas.cafe24.Cafe24FormUrlencodedFactory;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AccessToken;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AuthenticationClient;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.response.GetCafe24AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class ShopUseCaseImpl implements ShopUseCase {

	private final Cafe24AuthenticationClient cafe24AuthenticationClient;

	@Override
	public GetCafe24AccessTokenResponse getCafe24AccessToken(String mallId, String authCode) {
		MultiValueMap<String, String> requestParam = Cafe24FormUrlencodedFactory.getCafe24AccessToken(authCode);
		Cafe24AccessToken cafe24AccessToken = cafe24AuthenticationClient.getAccessToken(mallId, requestParam);
		return GetCafe24AccessTokenResponse.from(cafe24AccessToken);
	}
}
