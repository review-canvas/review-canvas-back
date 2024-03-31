package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.DesignItemSuper;
import com.romanticpipe.reviewcanvas.domain.ReviewItem;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.repository.DesignItemSuperRepository;
import com.romanticpipe.reviewcanvas.repository.ReviewItemRepository;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminCreater {
	private final ShopAdminRepository shopAdminRepository;
	private final DesignItemSuperRepository designItemSuperRepository;
	private final ReviewItemRepository reviewItemRepository;

	public void signUp(String email, String password, String name, String logoImageUrl, String mallNumber,
		String phoneNumber, Boolean title, Boolean author, Boolean point, Boolean media,
		Boolean content, Boolean createdAt, Boolean updatedAt, String themeName) {
		DesignItemSuper designItemSuper = designItemSuperRepository.findByThemeName(themeName);
		shopAdminRepository.save(
			new ShopAdmin(email, password, name, logoImageUrl, mallNumber, phoneNumber, designItemSuper.getId()));
		ShopAdmin shopAdmin = shopAdminRepository.findByEmail(email);
		reviewItemRepository.save(
			new ReviewItem(title, author, point, media, content, createdAt, updatedAt, shopAdmin.getId()));
	}
}
