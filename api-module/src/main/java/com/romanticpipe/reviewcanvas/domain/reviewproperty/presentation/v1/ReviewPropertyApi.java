package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.AuthInfo;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyForShopAdminResponse;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response.GetReviewPropertyForUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "ReviewProperty", description = "리뷰 속성 API")
public interface ReviewPropertyApi {

	@Operation(summary = "리뷰 디자인 전체 조회 API(삭제될 예정)", description = "ShopAdmin의 리뷰 디자인 속성 전체를 불러온다.",
		security = @SecurityRequirement(name = "Bearer Authentication"))
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 속성 조회가 완료되었습니다.")
	})
	@GetMapping("/shop-admin/review-properties")
	ResponseEntity<SuccessResponse<GetReviewPropertyForShopAdminResponse>> getReviewPropertyForShopAdmin(
		@AuthInfo JwtInfo jwtInfo
	);

	@Operation(summary = "리뷰 디자인 전체 조회 API(유저용)", description = "상품 상세 페이지에 필요한 review list 속성을 불러온다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 리뷰 속성 조회가 완료되었습니다.")
	})
	@GetMapping("/shop-admin/{mallId}/review-property")
	ResponseEntity<SuccessResponse<GetReviewPropertyForUserResponse>> getReviewPropertyForUser(
		@PathVariable String mallId
	);
}
