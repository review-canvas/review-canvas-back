package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Admin;
import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.AdminNotFoundException;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewDesignNotFoundException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopAdminValidator {
	private final ShopAdminRepository shopAdminRepository;
	private final ReviewDesignRepository designItemSuperRepository;

	public ReviewDesign isExistTheme(Long reviewDesignId) {
		ReviewDesign reviewDesign = designItemSuperRepository.findById(reviewDesignId)
			.orElseThrow(ReviewDesignNotFoundException::new);
		if (!reviewDesign.isGeneralType()) {
			throw new BusinessException(ShopAdminErrorCode.NOT_GENERAL_REVIEW_THEME);
		}
		return reviewDesign;
	}

	public ShopAdmin validByEmail(String email) {
		return shopAdminRepository.findByEmail(email)
			.orElseThrow(() -> new AdminNotFoundException());
	}

	public ShopAdmin validById(long shopAdminId) {
		return shopAdminRepository.findById(shopAdminId)
			.orElseThrow(() -> new AdminNotFoundException());
	}

	public Admin validByAuthId(long adminAuthId) {
		return shopAdminRepository.findByAdminAuthId(adminAuthId)
			.orElseThrow(() -> new AdminNotFoundException());
	}

	public boolean isExistEmail(String email) {
		return shopAdminRepository.existsByEmail(email);
	}
}
