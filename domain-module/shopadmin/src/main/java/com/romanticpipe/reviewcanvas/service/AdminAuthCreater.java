package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.repository.AdminAuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAuthCreater {
	private final AdminAuthRepository adminAuthRepository;

	public void save(String refreshToken, ShopAdmin shopAdmin) {
		AdminAuth adminAuth = adminAuthRepository.findByShopAdminId(shopAdmin.getId()).
			orElseGet(() -> new AdminAuth(shopAdmin.getId()));
		adminAuth.setRefreshToken(refreshToken);
		adminAuthRepository.save(adminAuth);
	}

}
