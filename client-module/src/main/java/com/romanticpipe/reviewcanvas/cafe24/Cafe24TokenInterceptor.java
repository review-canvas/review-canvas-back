package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AccessToken;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AuthenticationClient;
import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ShopAuthTokenNotFoundException;
import com.romanticpipe.reviewcanvas.service.ShopAuthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Cafe24TokenInterceptor implements ClientHttpRequestInterceptor {

	private final ShopAuthTokenService shopAuthTokenService;
	private final Cafe24AuthenticationClient cafe24AuthenticationClient;
	private final ConcurrentMap<String, Cafe24Token> cafe24Tokens;

	public Cafe24TokenInterceptor(ShopAuthTokenService shopAuthTokenService, Cafe24AuthenticationClient cafe24AuthenticationClient) {
		this.shopAuthTokenService = shopAuthTokenService;
		this.cafe24AuthenticationClient = cafe24AuthenticationClient;
		this.cafe24Tokens = this.shopAuthTokenService.findAll().stream()
			.collect(Collectors.toConcurrentMap(ShopAuthToken::getMallId, Cafe24Token::from));
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
		throws IOException {
		String mallId = extractMallId(request);
		String accessToken = getCafe24AccessToken(mallId);

		request.getHeaders().add("Authorization", "Bearer " + accessToken);
		return execution.execute(request, body);
	}

	private String extractMallId(HttpRequest request) {
		String mallId = request.getURI().getHost().split("\\.")[0];
		if (StringUtils.hasText(mallId)) {
			return mallId;
		}
		throw new IllegalArgumentException("cafe24 api 호출 시 mallId는 필수입니다.");
	}

	private String getCafe24AccessToken(String mallId) {
		Cafe24Token cafe24Token = getCafe24Token(mallId);
		if (isTokenExpired(cafe24Token, mallId)) {
			Cafe24AccessToken newToken = reissueAccessToken(cafe24Token, mallId);
			updateShopAuthToken(newToken);
			log.info("Cafe24 access token이 갱신되었습니다. [mallId: {}]", mallId);
			return newToken.accessToken();
		}
		return cafe24Token.accessToken();
	}

	private Cafe24Token getCafe24Token(String mallId) {
		return Optional.ofNullable(cafe24Tokens.get(mallId))
			.orElseThrow(ShopAuthTokenNotFoundException::new);
	}

	private boolean isTokenExpired(Cafe24Token cafe24Token, String mallId) {
		LocalDateTime now = LocalDateTime.now();
		if (now.isAfter(cafe24Token.refreshTokenExpiresAt())) {
			log.warn("Cafe24 refresh token이 만료되었습니다. [mallId: {}]", mallId);
			throw new BusinessException(Cafe24ErrorCode.INVALID_OR_EXPIRED_REFRESH_TOKEN);
		}
		return now.isAfter(cafe24Token.accessTokenExpiresAt());
	}

	private Cafe24AccessToken reissueAccessToken(Cafe24Token cafe24Token, String mallId) {
		return cafe24AuthenticationClient.reissueAccessToken(mallId,
			Cafe24FormUrlencodedFactory.reissueCafe24AccessToken(cafe24Token.refreshToken()));
	}

	private void updateShopAuthToken(Cafe24AccessToken newToken) {
		ShopAuthToken shopAuthToken = shopAuthTokenService.validateByMallId(newToken.mallId());
		shopAuthToken.update(newToken.accessToken(), newToken.expiresAt(), newToken.refreshToken(),
			newToken.refreshTokenExpiresAt(), String.join(",", newToken.scopes()));
		shopAuthTokenService.save(shopAuthToken);
		cafe24Tokens.put(newToken.mallId(), Cafe24Token.from(shopAuthToken));
	}
}
