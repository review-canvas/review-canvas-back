package com.romanticpipe.reviewcanvas;

import com.romanticpipe.reviewcanvas.domain.Product;
import org.springframework.test.util.ReflectionTestUtils;

public final class TestProductFactory {

	public static Product createProduct(Long productId, Long productNo, Integer shopAdminId) {
		Product product = new Product(productNo, "testProduct", shopAdminId);
		ReflectionTestUtils.setField(product, "id", productId);
		return product;
	}
}
