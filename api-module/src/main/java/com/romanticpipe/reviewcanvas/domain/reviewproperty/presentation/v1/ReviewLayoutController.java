package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewLayoutUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.LayoutRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewLayoutController implements ReviewLayoutApi {

	private final ReviewLayoutUseCase reviewLayoutUsecase;

	@Override
	@PatchMapping("/shop-admin/review-design/layout")
	public ResponseEntity<SuccessResponse<Void>> updateLayout(@AuthInfo JwtInfo jwtInfo,
		@RequestBody LayoutRequest layoutRequest) {

		reviewLayoutUsecase.updateLayout(jwtInfo.adminId(), layoutRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}
}
