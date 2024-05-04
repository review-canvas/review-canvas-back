package com.romanticpipe.reviewcanvas.admin.service;

import com.romanticpipe.reviewcanvas.admin.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.admin.repository.AdminAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthCreator {
	private final AdminAuthRepository adminAuthRepository;

	public void createShopAdminAuth(Integer shopAdminId) {
		adminAuthRepository.save(AdminAuth.createShopAdminAuth(shopAdminId));
	}

}
