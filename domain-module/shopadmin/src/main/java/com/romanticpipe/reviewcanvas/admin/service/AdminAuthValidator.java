package com.romanticpipe.reviewcanvas.admin.service;

import com.romanticpipe.reviewcanvas.admin.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;
import com.romanticpipe.reviewcanvas.admin.exception.AdminAuthNotFoundException;
import com.romanticpipe.reviewcanvas.admin.repository.AdminAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthValidator {
	private final AdminAuthRepository adminAuthRepository;

	public AdminAuth findByAdminId(Integer adminId, AdminRole role) {
		if (AdminRole.ROLE_SUPER_ADMIN.equals(role)) {
			return adminAuthRepository.findBySuperAdminId(adminId)
				.orElseThrow(AdminAuthNotFoundException::new);
		}
		return adminAuthRepository.findByShopAdminId(adminId)
			.orElseThrow(AdminAuthNotFoundException::new);
	}
}
