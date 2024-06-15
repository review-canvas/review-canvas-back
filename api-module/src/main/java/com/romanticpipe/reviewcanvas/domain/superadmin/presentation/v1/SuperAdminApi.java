package com.romanticpipe.reviewcanvas.domain.superadmin.presentation.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.superadmin.application.usecase.response.GetShopInfoResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "SuperAdmin", description = "슈퍼 어드민 API")
public interface SuperAdminApi {
	@Operation(summary = "샵 목록 조회 API", description = "회원가입된 샵들을 조회한다.")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 샵 조회가 완료되었습니다.")
	})
	@GetMapping("/super-admin/shops")
	ResponseEntity<SuccessResponse<PageResponse<GetShopInfoResponse>>> getShopInfos(
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page
	);
}
