package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewDesignNotFoundException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.exception.ShopAdminNotFoundException;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopAdminValidator {
	private final ShopAdminRepository shopAdminRepository;
	private final ReviewDesignRepository designItemSuperRepository;

	public ShopAdmin login(String email, String password) {
		Optional<ShopAdmin> shopAdmin = shopAdminRepository.findByEmail(email);
		if (shopAdmin.isPresent() && shopAdmin.get().getPassword().equals(password)) {
			return shopAdmin.get();
		} else {
			throw new ShopAdminNotFoundException();
		}
	}

	public ReviewDesign isExistTheme(Long reviewDesignId) {
		ReviewDesign reviewDesign = designItemSuperRepository.findById(reviewDesignId)
			.orElseThrow(ReviewDesignNotFoundException::new);
		if (!reviewDesign.isGeneralType()) {
			throw new BusinessException(ShopAdminErrorCode.NOT_GENERAL_REVIEW_THEME);
		}
		return reviewDesign;
	}

	public ShopAdmin isExsitUser(String email) {
		return shopAdminRepository.findByEmail(email)
			.orElseThrow(() -> new ShopAdminNotFoundException());
	}

	public ShopAdmin validById(long shopAdminId) {
		return shopAdminRepository.findById(shopAdminId)
			.orElseThrow(() -> new BusinessException(ShopAdminErrorCode.SHOP_ADMIN_NOT_FOUND));
	}
}
