package com.romanticpipe.reviewcanvas.cafe24.application;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@Component
@HttpExchange("https://{mallId}.cafe24api.com/api/v2")
public interface Cafe24ApplicationClient {

	@PostExchange(value = "/admin/scripttags")
	String createScriptTag(@PathVariable String mallId, @RequestBody Cafe24CreateScriptTagDto dto);
}
