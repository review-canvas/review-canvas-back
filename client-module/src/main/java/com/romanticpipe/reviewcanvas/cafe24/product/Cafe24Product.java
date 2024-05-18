package com.romanticpipe.reviewcanvas.cafe24.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.util.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Cafe24Product(Long productNo, String productName) {

	public boolean isFullContent() {
		return productNo != null && StringUtils.hasText(productName);
	}
}
