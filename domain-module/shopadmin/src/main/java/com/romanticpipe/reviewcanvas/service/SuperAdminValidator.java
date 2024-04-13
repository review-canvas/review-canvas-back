package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.AdminInterface;
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

	public AdminInterface validByAuthId(long adminAuthId) {
		return superAdminRepository.findByAdminAuthId(adminAuthId)
			.orElseThrow(() -> new BusinessException(SuperAdminErrorCode.SUPER_ADMIN_NOT_FOUND));
	}

	public AdminInterface validById(Long id) {
		return superAdminRepository.findById(id)
			.orElseThrow(() -> new BusinessException(SuperAdminErrorCode.SUPER_ADMIN_NOT_FOUND));
	}
}
