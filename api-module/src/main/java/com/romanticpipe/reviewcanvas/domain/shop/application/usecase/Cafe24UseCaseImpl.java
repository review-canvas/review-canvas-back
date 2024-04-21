package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.romanticpipe.reviewcanvas.cafe24.Cafe24FormUrlencodedFactory;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AccessToken;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AuthenticationClient;
import com.romanticpipe.reviewcanvas.service.ShopAdminTokenCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class Cafe24UseCaseImpl implements Cafe24UseCase {

	private final Cafe24AuthenticationClient cafe24AuthenticationClient;
	private final ShopAdminTokenCreator shopAdminTokenCreator;

	@Override
	public void cafe24AuthenticationProcess(String mallId, String authCode) {
		MultiValueMap<String, String> requestParam = Cafe24FormUrlencodedFactory.getCafe24AccessToken(authCode);
		Cafe24AccessToken cafe24AccessToken = cafe24AuthenticationClient.getAccessToken(mallId, requestParam);
		shopAdminTokenCreator.save(cafe24AccessToken.toShopAuthToken());
	}
}
