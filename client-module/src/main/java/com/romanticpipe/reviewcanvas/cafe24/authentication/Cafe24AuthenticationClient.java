package com.romanticpipe.reviewcanvas.cafe24.authentication;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@Component
@HttpExchange("https://{mallId}.cafe24api.com/api/v2")
public interface Cafe24AuthenticationClient {

	@PostExchange(value = "/oauth/token", contentType = "application/x-www-form-urlencoded")
	Cafe24AccessToken getAccessToken(@PathVariable String mallId,
									 @RequestParam MultiValueMap<String, String> requestParam);

	@PostExchange(value = "/oauth/token", contentType = "application/x-www-form-urlencoded")
	Cafe24AccessToken reissueAccessToken(@PathVariable String mallId,
										 @RequestParam MultiValueMap<String, String> requestParam);
}
