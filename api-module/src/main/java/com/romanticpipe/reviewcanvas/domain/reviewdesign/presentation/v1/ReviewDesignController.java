package com.romanticpipe.reviewcanvas.domain.reviewdesign.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.reviewdesign.application.usecase.response.GetReviewDesignResponse;
import com.romanticpipe.reviewcanvas.domain.reviewdesign.application.usecase.ReviewDesignUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewDesignController implements ReviewDesignApi{

	private final ReviewDesignUseCase reviewDesignUseCase;

	@GetMapping("/review_designs/selected/{shop_admin_id}")
	public ResponseEntity<SuccessResponse<GetReviewDesignResponse>> getSelectedReviewDesign(
		@PathVariable("shop_admin_id") long shopAdminId,
		@RequestParam String position
	) {
		return SuccessResponse.of(reviewDesignUseCase.getSelectedReviewDesign(shopAdminId, position)).asHttp(HttpStatus.OK);
	}

}
