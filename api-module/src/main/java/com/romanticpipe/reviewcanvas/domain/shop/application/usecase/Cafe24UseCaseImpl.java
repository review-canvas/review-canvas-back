package com.romanticpipe.reviewcanvas.domain.shop.application.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanticpipe.reviewcanvas.admin.domain.ShopAuthToken;
import com.romanticpipe.reviewcanvas.admin.service.ShopAdminReader;
import com.romanticpipe.reviewcanvas.admin.service.ShopAuthTokenService;
import com.romanticpipe.reviewcanvas.cafe24.Cafe24ErrorCode;
import com.romanticpipe.reviewcanvas.cafe24.Cafe24FormUrlencodedFactory;
import com.romanticpipe.reviewcanvas.cafe24.application.Cafe24ApplicationClient;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AccessToken;
import com.romanticpipe.reviewcanvas.cafe24.authentication.Cafe24AuthenticationClient;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.request.Cafe24CreateScriptTagRequest;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Cafe24UseCaseImpl implements Cafe24UseCase {

	private final TransactionTemplate writeTransactionTemplate;
	private final Cafe24AuthenticationClient cafe24AuthenticationClient;
	private final Cafe24ApplicationClient cafe24ApplicationClient;
	private final ShopAuthTokenService shopAuthTokenService;
	private final ShopAdminReader shopAdminReader;
	private final ObjectMapper objectMapper;

	@Override
	public String cafe24AuthenticationProcess(String mallId, String authCode) {
		MultiValueMap<String, String> requestParam = Cafe24FormUrlencodedFactory.getCafe24AccessToken(authCode);
		Cafe24AccessToken cafe24AccessToken = cafe24AuthenticationClient.getAccessToken(mallId, requestParam);
		if (!cafe24AccessToken.isFullContent()) {
			throw new BusinessException(Cafe24ErrorCode.INVALID_ACCESS_TOKEN);
		}
		return writeTransactionTemplate.execute(transactionStatus -> {
			try {
				Optional<ShopAuthToken> shopAuthToken = shopAuthTokenService.findByMallId(mallId);
				if (shopAuthToken.isPresent()) {
					updateShopAuthToken(cafe24AccessToken, shopAuthToken.get());
					return shopAdminReader.findByMallId(mallId)
						.map(admin -> "REGISTERED")
						.orElse("PREVIOUS_INSTALLED");
				}

				shopAuthTokenService.save(cafe24AccessToken.toShopAuthToken());
				return "INSTALLED";
			} catch (RuntimeException e) {
				transactionStatus.setRollbackOnly();
				throw e;
			}
		});
	}

	@Override
	public String cafe24CreateScriptTag(String mallId, Cafe24CreateScriptTagRequest request) {
		String response = cafe24ApplicationClient.createScriptTag(mallId, request.toCafe24CreateScriptTagDto());
		return getScriptNo(response);
	}

	private void updateShopAuthToken(Cafe24AccessToken cafe24AccessToken, ShopAuthToken shopAuthToken) {
		String scope = String.join(",", cafe24AccessToken.scopes());
		shopAuthToken.update(cafe24AccessToken.accessToken(), cafe24AccessToken.expiresAt(),
			cafe24AccessToken.refreshToken(), cafe24AccessToken.refreshTokenExpiresAt(), scope);
	}

	private String getScriptNo(String response) {
		try {
			return objectMapper.readTree(response).path("scripttag").path("script_no").asText();
		} catch (JsonProcessingException e) {
			throw new BusinessException(CommonErrorCode.OUTER_CLIENT_REQUEST_ERROR);
		}
	}
}
