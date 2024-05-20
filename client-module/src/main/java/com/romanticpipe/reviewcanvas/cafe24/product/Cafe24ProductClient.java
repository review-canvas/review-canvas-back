package com.romanticpipe.reviewcanvas.cafe24.product;

import com.romanticpipe.reviewcanvas.cafe24.Cafe24CountDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@Component
@HttpExchange("https://{mallId}.cafe24api.com/api/v2")
public interface Cafe24ProductClient {

	@GetExchange("/admin/products/count")
	Cafe24CountDto getProductCount(@PathVariable String mallId);

	@GetExchange(value = "/admin/products/{productNo}")
	Cafe24ProductDto getProduct(@PathVariable String mallId, @PathVariable Long productNo);

	@GetExchange(value = "/admin/products")
	Cafe24ProductsDto getProducts(@PathVariable String mallId);
}
