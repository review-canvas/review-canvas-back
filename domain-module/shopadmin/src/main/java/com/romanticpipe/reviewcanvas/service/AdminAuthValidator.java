package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.exception.AdminNotFoundException;
import com.romanticpipe.reviewcanvas.repository.AdminAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthValidator {
	private final AdminAuthRepository adminAuthRepository;

	public AdminAuth findById(Long id) {
		return adminAuthRepository.findById(id)
			.orElseThrow(() -> new AdminNotFoundException());
	}
}
