package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.repository.MyReviewDesignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyReviewDesignValidator {

	private final MyReviewDesignRepository myReviewDesignRepository;

	public void validateIsMyDesign(Integer shopAdminId, Integer reviewDesignId) {
		myReviewDesignRepository.findByShopAdminIdAndReviewDesignId(shopAdminId, reviewDesignId)
			.orElseThrow(() -> new BusinessException(ShopAdminErrorCode.NOT_REVIEW_DESIGN_OWNER));
	}
}
