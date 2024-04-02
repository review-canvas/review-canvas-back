package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ReviewItem;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.repository.ReviewItemRepository;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminCreater {
	private final ShopAdminRepository shopAdminRepository;
	private final ReviewItemRepository reviewItemRepository;

	public void signUp(ShopAdmin shopAdmin, ReviewItem reviewItem) {
		shopAdminRepository.save(shopAdmin);
		reviewItemRepository.save(reviewItem);
	}
}
