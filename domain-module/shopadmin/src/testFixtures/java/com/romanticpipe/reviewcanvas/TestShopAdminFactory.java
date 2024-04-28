package com.romanticpipe.reviewcanvas;

import java.time.LocalDateTime;

import org.springframework.test.util.ReflectionTestUtils;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignPosition;
import com.romanticpipe.reviewcanvas.domain.ReviewDesignType;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;

public final class TestShopAdminFactory {



	public static ShopAdmin createShopAdmin(String mallId, String email, String password, String mallName
		, String logoImageUrl, String mallNumber, String phoneNumber, Boolean approveStatus
		, String businessNumber) {
		ShopAdmin shopAdmin = new ShopAdmin(mallId, email, password, mallName, logoImageUrl, phoneNumber, mallNumber,
			approveStatus, businessNumber);
		return shopAdmin;
	}

}
