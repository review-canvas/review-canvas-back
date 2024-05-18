package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateShopAdminInfoRequest(
	@Schema(description = "비밀번호")
	String password,
	@Schema(description = "전화번호")
	String phoneNumber,
	@Schema(description = "관리자 전화번호")
	String mallNumber,
	@Schema(description = "이메일")
	String email,
	@Schema(description = "쇼핑몰 상호")
	String mallName
) {
}
