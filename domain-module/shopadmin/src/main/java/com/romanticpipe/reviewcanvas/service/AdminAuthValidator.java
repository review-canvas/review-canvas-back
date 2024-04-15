package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import com.romanticpipe.reviewcanvas.exception.AdminNotFoundException;
import com.romanticpipe.reviewcanvas.repository.AdminAuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAuthValidator {
	private final AdminAuthRepository adminAuthRepository;

	public AdminAuth findById(Long Id) {
		return adminAuthRepository.findById(Id)
			.orElseThrow(() -> new AdminNotFoundException());
	}
}
