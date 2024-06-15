package com.romanticpipe.reviewcanvas.domain.superadmin.presentation.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.superadmin.application.usecase.SuperAdminUseCase;
import com.romanticpipe.reviewcanvas.domain.superadmin.application.usecase.response.GetShopInfoResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SuperAdminController implements SuperAdminApi {

	private final SuperAdminUseCase superAdminUseCase;

	@Override
	@GetMapping("/super-admin/shops")
	public ResponseEntity<SuccessResponse<PageResponse<GetShopInfoResponse>>> getShopInfos(
		@RequestParam(value = "size", required = false, defaultValue = "10") int size,
		@RequestParam(value = "page", required = false, defaultValue = "0") int page
	) {
		return SuccessResponse.of(superAdminUseCase.getShopInfos(PageableRequest.of(page, size))).asHttp(HttpStatus.OK);
	}
}
