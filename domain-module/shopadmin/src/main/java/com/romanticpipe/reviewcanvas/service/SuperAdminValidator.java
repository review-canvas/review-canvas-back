package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.SuperAdmin;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.SuperAdminErrorCode;
import com.romanticpipe.reviewcanvas.repository.SuperAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuperAdminValidator {
	private final SuperAdminRepository superAdminRepository;

	public SuperAdmin validByEmail(String email) {
		return this.superAdminRepository.findByEmail(email)
			.orElseThrow(() -> new BusinessException(SuperAdminErrorCode.SUPER_ADMIN_NOT_FOUND));
	}

	public SuperAdmin validById(long adminId) {
		return superAdminRepository.findById(adminId)
			.orElseThrow(() -> new BusinessException(SuperAdminErrorCode.SUPER_ADMIN_NOT_FOUND));
	}
}
