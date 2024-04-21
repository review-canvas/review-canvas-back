package com.romanticpipe.reviewcanvas.domain.shop.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.Cafe24UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Cafe24Controller implements Cafe24Api {

	private final Cafe24UseCase cafe24UseCase;

	@Override
	@GetMapping("/cafe24/authentication-process")
	public ResponseEntity<SuccessResponse<Void>> cafe24AuthenticationProcess(
		@RequestParam(required = true) String mallId,
		@RequestParam(required = true) String authCode
	) {
		cafe24UseCase.cafe24AuthenticationProcess(mallId, authCode);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}
}
