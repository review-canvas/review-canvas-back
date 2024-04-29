package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.repository.MyReviewDesignRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyReviewDesignValidator {

	private final MyReviewDesignRepository myReviewDesignRepository;

	public void validateIsMyDesign(Integer shopAdminId, Integer reviewDesignId) {
		myReviewDesignRepository.findByShopAdminIdAndReviewListDesignId(shopAdminId, reviewDesignId)
			.orElseThrow(() -> new BusinessException(ShopAdminErrorCode.NOT_REVIEW_DESIGN_OWNER));
	}
}
