package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ProductUseCase;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetProductResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController implements ProductApi {

	private final ProductUseCase productUseCase;

	@Override
	@GetMapping("/shops/{shopAdminId}/products")
	public ResponseEntity<SuccessResponse<PageResponse<GetProductResponse>>> getProducts(
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page,
		@AuthInfo JwtInfo jwtInfo, @PathVariable Integer shopAdminId) {
		return SuccessResponse.of(
			productUseCase.getProducts(jwtInfo, shopAdminId, PageableRequest.of(page, size))
		).asHttp(HttpStatus.OK);
	}
}
