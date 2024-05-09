package com.romanticpipe.reviewcanvas.admin.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.admin.domain.Admin;
import com.romanticpipe.reviewcanvas.admin.domain.SuperAdmin;
import com.romanticpipe.reviewcanvas.admin.exception.AdminNotFoundException;
import com.romanticpipe.reviewcanvas.admin.repository.SuperAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuperAdminService {
	private final SuperAdminRepository superAdminRepository;

	public SuperAdmin validByEmail(String email) {
		return this.superAdminRepository.findByEmail(email)
			.orElseThrow(() -> new AdminNotFoundException());
	}

	public Admin validById(Integer superAdminId) {
		return superAdminRepository.findById(superAdminId)
			.orElseThrow(AdminNotFoundException::new);
	}
}
