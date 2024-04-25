package com.romanticpipe.reviewcanvas.cafe24.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record Cafe24ProductDto(@Getter Cafe24Product product) {
	@JsonCreator
	public Cafe24ProductDto {
	}

	public void validateCafe24Product(String mallId, Long productNo) {
		if (!product.isFullContent()) {
			log.info("Cafe24로부터 [mallId: {}] [productNo: {}]에 해당하는 상품을 가져올 수 없습니다.", mallId, productNo);
			throw new Cafe24ProductNotFoundException();
		}
	}
}
