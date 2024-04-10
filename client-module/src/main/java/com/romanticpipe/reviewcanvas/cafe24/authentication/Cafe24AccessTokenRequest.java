package com.romanticpipe.reviewcanvas.cafe24.authentication;

import static com.romanticpipe.reviewcanvas.cafe24.Cafe24Properties.REDIRECT_URI;

public record Cafe24AccessTokenRequest(String code, String grantType, String redirectUri) {

	public static Cafe24AccessTokenRequest of(String code) {
		return new Cafe24AccessTokenRequest(code, "authorization_code", REDIRECT_URI);
	}
}
