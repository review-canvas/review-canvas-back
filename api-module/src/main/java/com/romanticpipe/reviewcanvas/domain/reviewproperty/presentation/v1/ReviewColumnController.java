package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewColumnUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateColumnRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewColumnResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewColumnController implements ReviewColumnApi {

	private final ReviewColumnUseCase reviewColumnUseCase;

	@Override
	@GetMapping("/shop-admin/review-column")
	public ResponseEntity<SuccessResponse<GetReviewColumnResponse>> getColumn(@AuthInfo JwtInfo jwtInfo) {
		return SuccessResponse.of(
			GetReviewColumnResponse.from(
				reviewColumnUseCase.getColumnByShopAdminId(jwtInfo.adminId()))
		).asHttp(HttpStatus.OK);
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
