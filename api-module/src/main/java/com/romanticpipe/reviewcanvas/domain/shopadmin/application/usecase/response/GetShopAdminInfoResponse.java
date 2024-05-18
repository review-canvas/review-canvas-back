package com.romanticpipe.reviewcanvas.domain.shopadmin.application.usecase.response;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record GetShopAdminInfoResponse(
	@Schema(description = "이메일")
	String email,
	@Schema(description = "전화번호")
	String mallNumber,
	@Schema(description = "관리자 전화번호")
	String phoneNumber,
	@Schema(description = "상호명")
	String mallName,
	@Schema(description = "사업자 등록번호")
	String businessNumber
) {
	public static GetShopAdminInfoResponse from(ShopAdmin shopAdmin) {
		return GetShopAdminInfoResponse.builder()
			.email(shopAdmin.getEmail())
			.mallNumber(shopAdmin.getMallNumber())
			.phoneNumber(shopAdmin.getPhoneNumber())
			.mallName(shopAdmin.getMallName())
			.businessNumber(shopAdmin.getBusinessNumber())
			.build();
	}
}
