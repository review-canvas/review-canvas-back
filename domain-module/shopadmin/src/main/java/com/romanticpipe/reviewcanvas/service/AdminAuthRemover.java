package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.repository.AdminAuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAuthRemover {
	private static final String DELETED_TOKEN = "deleted";

	private final AdminAuthRepository adminAuthRepository;

	public void deleteRefreshToken(Long id) {
		AdminAuth adminAuth = adminAuthRepository.findByAdminId(id).get();
		adminAuth.setRefreshToken(DELETED_TOKEN);
	}

}
