package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.repository.AdminAuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAuthCreater {
	private final AdminAuthRepository adminAuthRepository;

	public void save(AdminAuth adminAuth) {
		adminAuthRepository.save(adminAuth);
	}

}
