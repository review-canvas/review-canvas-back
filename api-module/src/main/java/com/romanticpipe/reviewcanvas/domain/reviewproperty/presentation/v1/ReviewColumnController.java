package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewColumnUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateColumnRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewColumnController implements ReviewColumnApi {

	private final ReviewColumnUseCase reviewColumnUseCase;

	@Override
	@GetMapping("/shop-admin/review-column/shopadmin/{shopAdminId}")
	public ResponseEntity<SuccessResponse<ReviewColumn>> getColumn(@PathVariable Integer shopAdminId) {
		return SuccessResponse.of(reviewColumnUseCase.getColumnByShopAdminId(shopAdminId)).asHttp(HttpStatus.OK);
	}

	@Override
	@PatchMapping("/shop-admin/review-column")
	public ResponseEntity<SuccessResponse<Void>> updateColumn(@AuthInfo JwtInfo jwtInfo,
															  @RequestBody UpdateColumnRequest updateColumnRequest) {
		reviewColumnUseCase.updateReviewColumn(jwtInfo.adminId(), updateColumnRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@PatchMapping("/shop-admin/review-column/reset")
	public ResponseEntity<SuccessResponse<Void>> resetColumn(@AuthInfo JwtInfo jwtInfo) {
		reviewColumnUseCase.resetReviewColumn(jwtInfo.adminId());
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}
}
