package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.ReviewDesignUseCase;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.UpdateDesignViewRequest;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignViewResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewDesignWriteResponse;
import jakarta.validation.Valid;
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
public class ReviewDesignController implements ReviewDesignApi {

	private final ReviewDesignUseCase reviewDesignUseCase;

	@Override
	@GetMapping("/shop-admin/review-design-view")
	public ResponseEntity<SuccessResponse<GetReviewDesignViewResponse>> getReviewDesignView(@AuthInfo JwtInfo jwtInfo) {
		return SuccessResponse.of(
			reviewDesignUseCase.getReviewDesignView(jwtInfo.adminId())
		).asHttp(HttpStatus.OK);
	}

	@Override
	@PatchMapping("/shop-admin/review-design-view")
	public ResponseEntity<SuccessResponse<Void>> updateReviewDesignView(
		@AuthInfo JwtInfo jwtInfo,
		@Valid @RequestBody UpdateDesignViewRequest updateDesignViewRequest
	) {
		reviewDesignUseCase.updateReviewDesignView(jwtInfo.adminId(), updateDesignViewRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@PatchMapping("/shop-admin/review-design-view/reset")
	public ResponseEntity<SuccessResponse<Void>> resetReviewDesignView(
		@AuthInfo JwtInfo jwtInfo
	) {
		reviewDesignUseCase.resetReviewDesignView(jwtInfo.adminId());
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/shop-admin/review-design-write")
	public ResponseEntity<SuccessResponse<GetReviewDesignWriteResponse>> getReviewDesignWrite(@AuthInfo JwtInfo jwtInfo
	) {
		return SuccessResponse.of(
			reviewDesignUseCase.getReviewDesignWrite(jwtInfo.adminId())
		).asHttp(HttpStatus.OK);
	}
}
