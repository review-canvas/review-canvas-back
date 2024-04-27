package com.romanticpipe.reviewcanvas.domain.shop.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.Cafe24UseCase;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.request.Cafe24CreateScriptTagRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Cafe24Controller implements Cafe24Api {

	private final Cafe24UseCase cafe24UseCase;

	@Override
	@PostMapping("/cafe24/{mallId}/authentication-process")
	public ResponseEntity<SuccessResponse<Void>> cafe24AuthenticationProcess(
		@PathVariable(required = true) String mallId,
		@RequestParam(required = true) String authCode
	) {
		cafe24UseCase.cafe24AuthenticationProcess(mallId, authCode);
		return SuccessResponse.ofNoData().asHttp(HttpStatus.OK);
	}


	@Override
	@PostMapping("/cafe24/{mallId}/script-tag")
	public ResponseEntity<SuccessResponse<Map<String, String>>> cafe24CreateScriptTag(
		@PathVariable(required = true) String mallId,
		@Valid @RequestBody Cafe24CreateScriptTagRequest request
	) {
		String scriptNo = cafe24UseCase.cafe24CreateScriptTag(mallId, request);
		return SuccessResponse.of(
			Map.of("scriptNo", scriptNo)
		).asHttp(HttpStatus.OK);
	}
}
