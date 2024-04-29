package com.romanticpipe.reviewcanvas.service;

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
	private final ReviewDesignRepository reviewDesignRepository;

	public ReviewDesign isExistTheme(Integer reviewDesignId) {
		ReviewDesign reviewDesign = reviewDesignRepository.findById(reviewDesignId)
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

	public ShopAdmin validById(Integer shopAdminId) {
		return shopAdminRepository.findById(shopAdminId)
			.orElseThrow(AdminNotFoundException::new);
	}

	public boolean isExistEmail(String email) {
		return shopAdminRepository.existsByEmail(email);
	}

	public ShopAdmin validByMallId(String mallId) {
		return shopAdminRepository.findByMallId(mallId)
			.orElseThrow(AdminNotFoundException::new);
	}

	public void validateEmailDuplicated(String email) {
		shopAdminRepository.findByEmail(email)
			.ifPresent(admin -> {
				throw new BusinessException(ShopAdminErrorCode.DUPLICATED_EMAIL);
			});
	}
}
