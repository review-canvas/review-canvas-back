package com.romanticpipe.reviewcanvas.cafe24.users;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.romanticpipe.reviewcanvas.cafe24.product.Cafe24ProductDto;

@Component
@HttpExchange("https://{mallId}.cafe24api.com/api/v2")
public interface Cafe24UserClient {

	@GetExchange(value = "/admin/customersprivacy/{memberId}")
	Cafe24ProductDto getUser(@PathVariable String mallId, @PathVariable String memberId);
}
