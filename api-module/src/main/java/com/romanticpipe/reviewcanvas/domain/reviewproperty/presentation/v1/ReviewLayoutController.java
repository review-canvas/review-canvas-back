package com.romanticpipe.reviewcanvas.domain.reviewproperty.presentation.v1;

import org.springframework.http.ResponseEntity;

import com.romanticpipe.reviewcanvas.common.dto.SuccessResponse;
import com.romanticpipe.reviewcanvas.common.security.JwtInfo;
import com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request.LayoutRequest;

public class ReviewLayoutController implements ReviewLayoutApi {
	@Override
	public ResponseEntity<SuccessResponse<Void>> saveLayout(JwtInfo jwtInfo, LayoutRequest layoutRequest) {
		return null;
	}
}
