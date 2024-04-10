package com.romanticpipe.reviewcanvas.cafe24.authentication;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@Component
@HttpExchange("https://{mallId}.cafe24api.com/api/v2")
public interface Cafe24AuthenticationClient {

	@PostExchange(value = "/oauth/token", contentType = "application/x-www-form-urlencoded")
	Cafe24AccessToken getAccessToken(@PathVariable String mallId, @RequestPart Cafe24AccessTokenRequest requestBody);

}
