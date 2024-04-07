package com.romanticpipe.reviewcanvas.cafe24;

import com.romanticpipe.reviewcanvas.exception.ClientErrorCode;
import com.romanticpipe.reviewcanvas.exception.WebClientException;
import com.romanticpipe.reviewcanvas.util.SystemEnv;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Cafe24Client {

	private static final String GET_ACCESS_TOKEN_URL = "https://%s.cafe24api.com/api/v2/oauth/token";
	private static final String REDIRECT_URI = SystemEnv.get("CAFE24_REDIRECT_URI");
	private static final String AUTHORIZATION_CODE = SystemEnv.get("CAFE24_AUTHORIZATION_CODE");

	private final WebClient cafe24WebClient;

	public Cafe24AccessToken getCafe24AccessToken(String mallId, String authCode) {
		String uri = String.format(GET_ACCESS_TOKEN_URL, mallId);

		return cafe24WebClient
			.post()
			.uri(uri)
			.header(HttpHeaders.AUTHORIZATION, "Basic " + AUTHORIZATION_CODE)
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.body(BodyInserters.fromFormData("grant_type", "authorization_code")
				.with("code", authCode)
				.with("redirect_uri", REDIRECT_URI))
			.retrieve()
			.onStatus(HttpStatusCode::isError, response -> {
				return response.bodyToMono(String.class).flatMap(body ->
					Mono.error(new WebClientException(ClientErrorCode.CAFE24_API_ERROR, body)));
			})
			.bodyToMono(Cafe24AccessToken.class)
			.block();
	}
}
