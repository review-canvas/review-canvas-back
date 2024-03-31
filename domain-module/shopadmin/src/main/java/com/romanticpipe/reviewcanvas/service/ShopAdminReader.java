package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminReader {
	private final ShopAdminRepository shopAdminRepository;

	public ShopAdmin findByEmail(String email) {
		return this.shopAdminRepository.findByEmail(email)
			.orElseThrow(() -> new BusinessException(ShopAdminErrorCode.SHOP_ADMIN_NOT_FOUND));
	}

	public ShopAdmin findById(Long shopAdminId) {
		return this.shopAdminRepository.findById(shopAdminId)
			.orElseThrow(() -> new BusinessException(ShopAdminErrorCode.SHOP_ADMIN_NOT_FOUND));
	}
}
