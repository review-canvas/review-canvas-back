package com.romanticpipe.reviewcanvas.domain.shopadmin.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.ReviewDesignUseCase;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request.UpdateReviewDesignRequest;
import com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response.GetApplicableReviewDesignResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewDesignController implements ReviewDesignApi {

	private final ReviewDesignUseCase reviewDesignUseCase;

	@Override
	@PatchMapping("/shop-admin/review-design/{reviewDesignId}")
	public ResponseEntity<SuccessResponse<Void>> updateReviewDesign(
		@AuthInfo JwtInfo jwtInfo, @PathVariable Integer reviewDesignId,
		@Valid @RequestBody UpdateReviewDesignRequest updateReviewDesignRequest) {
		reviewDesignUseCase.updateReviewDesign(jwtInfo.adminId(), reviewDesignId, updateReviewDesignRequest);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}

	@Override
	@GetMapping("/review-designs")
	public ResponseEntity<SuccessResponse<List<GetApplicableReviewDesignResponse>>> getApplicableReviewDesign(
		@AuthInfo JwtInfo jwtInfo) {
		return SuccessResponse.of(
			reviewDesignUseCase.getApplicableReviewDesign(jwtInfo.adminId())
		).asHttp(HttpStatus.OK);
	}
}
