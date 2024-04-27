package com.romanticpipe.reviewcanvas.domain.shop.presentation.v1;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.domain.shop.application.usecase.request.Cafe24CreateScriptTagRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "cafe24", description = "Cafe24 쇼핑몰 API")
public interface Cafe24Api {

	@Operation(summary = "cafe24 인증 프로세스 api", description = "auth code로 cafe24 액세스 토큰을 발급받아 서버에 저장한다 "
		+ "https://developers.cafe24.com/app/front/app/develop/oauth/token")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "성공적으로 인증 프로세스를 수행했습니다."),
		@ApiResponse(
			responseCode = "400",
			description = "C003: 외부 API 호출 중 오류가 발생했습니다.",
			content = @Content(schema = @Schema(hidden = true))),
	})
	@PostMapping("/cafe24/{mallId}/authentication-process")
	ResponseEntity<SuccessResponse<Void>> cafe24AuthenticationProcess(
		@Schema(name = "mall id", description = "쇼핑몰의 아이디") @PathVariable(required = true) String mallId,
		@Schema(name = "authorization code", description = "인증 코드") @RequestParam(required = true) String authCode
	);

	@Operation(summary = "cafe24 script tag 생성 api",
		description = "https://developers.cafe24.com/docs/ko/api/admin/#create-a-script-tag 참고")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "cafe24 script tag 생성에 성공했습니다."),
		@ApiResponse(
			responseCode = "400",
			description = "C003: 외부 API 호출 중 오류가 발생했습니다.",
			content = @Content(schema = @Schema(hidden = true))),
	})
	@PostMapping("/cafe24/{mallId}/script-tag")
	ResponseEntity<SuccessResponse<String>> cafe24CreateScriptTag(
		@Schema(name = "mall id", description = "쇼핑몰의 아이디") @PathVariable(required = true) String mallId,
		@Valid @RequestBody Cafe24CreateScriptTagRequest request
	);
}
