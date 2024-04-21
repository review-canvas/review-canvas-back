package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Admin;
import com.romanticpipe.reviewcanvas.domain.SuperAdmin;
import com.romanticpipe.reviewcanvas.exception.AdminNotFoundException;
import com.romanticpipe.reviewcanvas.repository.SuperAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperAdminValidator {
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
