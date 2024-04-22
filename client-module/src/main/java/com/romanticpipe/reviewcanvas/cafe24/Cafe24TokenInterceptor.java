package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AccessToken;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AuthenticationClient;
import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.service.ShopAuthTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Cafe24TokenInterceptor implements ClientHttpRequestInterceptor {

	private final ShopAuthTokenService shopAuthTokenService;
	private final Cafe24AuthenticationClient cafe24AuthenticationClient;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		String mallId = extractMallId(request);
		ShopAuthToken shopAuthToken = shopAuthTokenService.validateByMallId(mallId);
		reissueAccessTokenIfNeeded(shopAuthToken, mallId);

		request.getHeaders().add("Authorization", "Bearer " + shopAuthToken.getAccessToken());
		return execution.execute(request, body);
	}

	private String extractMallId(HttpRequest request) {
		String mallId = request.getURI().getHost().split("\\.")[0];
		if (StringUtils.hasText(mallId)) {
			return mallId;
		}
		throw new IllegalArgumentException("cafe24 api 호출 시 mallId는 필수입니다.");
	}

	private void reissueAccessTokenIfNeeded(ShopAuthToken shopAuthToken, String mallId) {
		LocalDateTime now = LocalDateTime.now();
		if (now.isAfter(shopAuthToken.getRefreshTokenExpiresAt())) {
			throw new BusinessException(Cafe24ErrorCode.INVALID_OR_EXPIRED_REFRESH_TOKEN);
		}
		if (now.isAfter(shopAuthToken.getAccessTokenExpiresAt())) {
			Cafe24AccessToken newToken = reissueAccessToken(shopAuthToken, mallId);
			updateShopAuthToken(shopAuthToken, newToken);
		}
	}

	private Cafe24AccessToken reissueAccessToken(ShopAuthToken shopAuthToken, String mallId) {
		return cafe24AuthenticationClient.reissueAccessToken(mallId,
			Cafe24FormUrlencodedFactory.reissueCafe24AccessToken(shopAuthToken.getRefreshToken()));
	}

	private void updateShopAuthToken(ShopAuthToken shopAuthToken, Cafe24AccessToken newToken) {
		shopAuthToken.update(newToken.accessToken(), newToken.expiresAt(), newToken.refreshToken(),
			newToken.refreshTokenExpiresAt(), String.join(",", newToken.scopes()));
		shopAuthTokenService.save(shopAuthToken);
	}
}
