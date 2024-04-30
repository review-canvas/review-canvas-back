package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewContainerUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.reponse.GetReviewContainerResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateContainerRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewContainerController implements ReviewContainerApi {

	private final ReviewContainerUseCase reviewContainerUseCase;

	@Override
	@GetMapping("/shop-admin/review-container")
	public ResponseEntity<SuccessResponse<GetReviewContainerResponse>> getReviewContainer(@AuthInfo JwtInfo jwtInfo) {
		return SuccessResponse.of(
			reviewContainerUseCase.getReviewContainer(jwtInfo.adminId())
		).asHttp(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SuccessResponse<Void>> updateReviewContainer(
		@AuthInfo JwtInfo jwtInfo,
		@Valid @RequestBody UpdateContainerRequest updateContainerRequest) {
		reviewContainerUseCase.updateReviewContainer(jwtInfo.adminId(), updateContainerRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}
}
