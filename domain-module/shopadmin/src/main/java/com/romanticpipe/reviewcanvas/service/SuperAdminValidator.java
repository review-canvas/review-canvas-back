package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.SuperAdmin;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.repository.SuperAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuperAdminValidator {
	private final SuperAdminRepository superAdminRepository;

	public SuperAdmin isExsitUser(String email) {
		return this.superAdminRepository.findByEmail(email)
			.orElseThrow(() -> new BusinessException(ShopAdminErrorCode.SHOP_ADMIN_NOT_FOUND));
	}

	public SuperAdmin findById(Long shopAdminId) {
		return this.superAdminRepository.findById(shopAdminId)
			.orElseThrow(() -> new BusinessException(ShopAdminErrorCode.SHOP_ADMIN_NOT_FOUND));
	}
}
