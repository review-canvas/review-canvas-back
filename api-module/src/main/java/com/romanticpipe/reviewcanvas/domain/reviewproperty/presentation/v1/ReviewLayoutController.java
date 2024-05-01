package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewLayoutUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateLayoutRequest;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewLayoutController implements ReviewLayoutApi {

	private final ReviewLayoutUseCase reviewLayoutUsecase;

	@Override
	@PatchMapping("/shop-admin/review-layout")
	public ResponseEntity<SuccessResponse<Void>> updateLayout(@AuthInfo JwtInfo jwtInfo,
															  @RequestBody UpdateLayoutRequest updateLayoutRequest) {

		reviewLayoutUsecase.updateLayout(jwtInfo.adminId(), updateLayoutRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shop-admin/review-column/shopadmin/{shopAdminId}")
	public ResponseEntity<SuccessResponse<ReviewColumn>> getColumn(@PathVariable Integer shopAdminId) {
		return SuccessResponse.of(reviewLayoutUsecase.getColumn(shopAdminId)).asHttp(HttpStatus.OK);
	}
}
