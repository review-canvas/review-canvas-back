package com.romanticpipe.reviewcanvas.cafe24.product;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record Cafe24ProductDto(@Getter Cafe24Product product) {

	public void validateIsFullContent(String mallId, Long productNo) {
		if (!product.isFullContent()) {
			log.info("Cafe24로부터 [mallId: {}] [productNo: {}]에 해당하는 상품을 가져올 수 없습니다.", mallId, productNo);
			throw new Cafe24ProductNotFoundException();
		}
	}
}
