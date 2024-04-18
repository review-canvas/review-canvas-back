package com.romanticpipe.reviewcanvas.cafe24;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.romanticpipe.reviewcanvas.cafe24.Cafe24Properties.REDIRECT_URI;

public class Cafe24FormUrlencodedFactory {

	public static MultiValueMap<String, String> getCafe24AccessToken(String authCode) {
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.put("code", List.of(authCode));
		requestBody.put("grant_type", List.of("authorization_code"));
		requestBody.put("redirect_uri", List.of(REDIRECT_URI));
		return requestBody;
	}

	public static MultiValueMap<String, String> reissueCafe24AccessToken(String refreshToken) {
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.put("refresh_token", List.of(refreshToken));
		requestBody.put("grant_type", List.of("refresh_token"));
		return requestBody;
	}
}
