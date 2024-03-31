package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.exception.ShopAdminNotFoundException;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminValidator {
	private final ShopAdminRepository shopAdminRepository;

	public ShopAdmin login(String email, String password) {
		ShopAdmin shopAdmin = this.shopAdminRepository.findByEmail(email)
			.orElseThrow(() -> new BusinessException(ShopAdminErrorCode.SHOP_ADMIN_NOT_FOUND));

		if (shopAdmin != null && shopAdmin.getPassword().equals(password)) {
			return shopAdmin;
		} else {
			throw new ShopAdminNotFoundException();
		}

	}

	public ShopAdmin loginByAccesstoken(ShopAdmin loggedInShopAdmin) {
		return shopAdminRepository.findByEmail(loggedInShopAdmin.getEmail())
			.orElseThrow(() -> new BusinessException(ShopAdminErrorCode.SHOP_ADMIN_NOT_FOUND));
	}
}
