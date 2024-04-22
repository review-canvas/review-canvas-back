package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.romanticpipe.reviewcanvas.cafe24.Cafe24FormUrlencodedFactory;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AccessToken;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AuthenticationClient;
import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;
import com.romanticpipe.reviewcanvas.service.ShopAuthTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class Cafe24UseCaseImpl implements Cafe24UseCase {

	private final TransactionTemplate writeTransactionTemplate;
	private final Cafe24AuthenticationClient cafe24AuthenticationClient;
	private final ShopAuthTokenService shopAuthTokenService;

	@Override
	public void cafe24AuthenticationProcess(String mallId, String authCode) {
		MultiValueMap<String, String> requestParam = Cafe24FormUrlencodedFactory.getCafe24AccessToken(authCode);
		Cafe24AccessToken cafe24AccessToken = cafe24AuthenticationClient.getAccessToken(mallId, requestParam);
		writeTransactionTemplate.executeWithoutResult(transactionStatus -> {
			try {
				shopAuthTokenService.findByMallId(mallId)
					.ifPresentOrElse(shopAuthToken -> updateShopAuthToken(cafe24AccessToken, shopAuthToken),
						() -> shopAuthTokenService.save(cafe24AccessToken.toShopAuthToken()));
			} catch (RuntimeException e) {
				transactionStatus.setRollbackOnly();
				throw e;
			}
		});
	}

	private void updateShopAuthToken(Cafe24AccessToken cafe24AccessToken, ShopAuthToken shopAuthToken) {
		String scope = String.join(",", cafe24AccessToken.scopes());
		shopAuthToken.update(cafe24AccessToken.accessToken(), cafe24AccessToken.expiresAt(),
			cafe24AccessToken.refreshToken(), cafe24AccessToken.refreshTokenExpiresAt(), scope);
	}
}
