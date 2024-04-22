package com.romanticpipe.reviewcanvas.cafe24.product;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Map;

@Component
@HttpExchange("https://{mallId}.cafe24api.com/api/v2")
public interface Cafe24ProductClient {

	@GetExchange(value = "/admin/products/{productNo}")
	Map<String, Object> getProduct(@PathVariable String mallId, @PathVariable Long productNo);
}
