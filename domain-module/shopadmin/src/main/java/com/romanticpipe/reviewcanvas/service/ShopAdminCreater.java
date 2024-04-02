package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ReviewVisibility;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.repository.ReviewVisibilityRepository;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminCreater {
	private final ShopAdminRepository shopAdminRepository;
	private final ReviewVisibilityRepository reviewItemRepository;

	public void signUp(ShopAdmin shopAdmin) {
		shopAdminRepository.save(shopAdmin);
	}

	public void signUp(ReviewVisibility reviewItem) {
		reviewItemRepository.save(reviewItem);
	}
}
