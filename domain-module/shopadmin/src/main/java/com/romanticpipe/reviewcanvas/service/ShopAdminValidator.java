package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewDesignNotFoundException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.exception.ShopAdminNotFoundException;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

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
			.orElseThrow(() -> new ShopAdminNotFoundException());
	}

	public ShopAdmin validById(long shopAdminId) {
		return shopAdminRepository.findById(shopAdminId)
			.orElseThrow(() -> new ShopAdminNotFoundException());
	}

	public ShopAdmin validByAuthId(long adminAuthId) {
		return shopAdminRepository.findByAdminAuthId(adminAuthId)
			.orElseThrow(() -> new ShopAdminNotFoundException());
	}
}
