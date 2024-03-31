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
		AdminAuth adminAuth = adminAuthRepository.findByShopAdmin(shopAdmin).
			orElseGet(() -> new AdminAuth(shopAdmin));
		adminAuth.setRefreshToken(refreshToken);
		adminAuthRepository.save(adminAuth);
	}

}
