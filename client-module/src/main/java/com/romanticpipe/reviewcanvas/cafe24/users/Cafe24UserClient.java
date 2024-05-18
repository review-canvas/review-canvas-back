package com.romanticpipe.reviewcanvas.cafe24.users;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@Component
@HttpExchange("https://{mallId}.cafe24api.com/api/v2/admin")
public interface Cafe24UserClient {

	@GetExchange(value = "/customersprivacy/{memberId}")
	Cafe24UserDto getUser(@PathVariable String mallId, @PathVariable String memberId);
}
