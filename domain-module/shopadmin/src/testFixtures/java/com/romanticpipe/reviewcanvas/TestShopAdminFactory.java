package com.romanticpipe.reviewcanvas;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;

public final class TestShopAdminFactory {

	public static ShopAdmin createApproveShopAdmin(String mallId, String email, String password, String mallName, String logoImageUrl,
		String mallNumber, String phoneNumber, String businessNumber) {

		return new ShopAdmin(mallId, email, password, mallName, logoImageUrl, mallNumber, phoneNumber,
			true, businessNumber);
	}
}
