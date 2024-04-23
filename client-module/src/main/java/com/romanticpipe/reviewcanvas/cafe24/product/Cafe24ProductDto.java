package com.romanticpipe.reviewcanvas.cafe24.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class Cafe24ProductDto {
	private final Cafe24Product product;

	@JsonCreator
	public Cafe24ProductDto(Cafe24Product product) {
		this.product = product;
	}

	public Long getProductNo() {
		return product.productNo();
	}

	public String getProductName() {
		return product.productName();
	}

	public void validateCafe24Product(String mallId, Long productNo) {
		if (!StringUtils.hasText(getProductName())) {
			log.info("Cafe24로부터 [mallId: {}] [productNo: {}]에 해당하는 상품을 가져올 수 없습니다.", mallId, productNo);
			throw new Cafe24ProductNotFoundException();
		}
	}
}
