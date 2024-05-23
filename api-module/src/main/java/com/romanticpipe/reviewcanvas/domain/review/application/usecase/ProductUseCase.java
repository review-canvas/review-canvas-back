package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetProductResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

public interface ProductUseCase {
	Product createProduct(String mallId, Long productNo);

	PageResponse<GetProductResponse> getProducts(JwtInfo shopAdminId, Integer loginAdminId, PageableRequest pageable);
}
