package com.romanticpipe.reviewcanvas.common.healthcheck;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {

	@Operation(summary = "서버 생존 여부 체크용", description = "서버가 살아있는지 체크합니다.")
	@ApiResponse(responseCode = "200", description = "서버 생존")
	@GetMapping(value = "/health")
	public ResponseEntity<Void> verifyServerAlive() {
		return ResponseEntity.ok().build();
	}
}
