package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminCreater {
	private final ShopAdminRepository shopAdminRepository;

	public void signUp(String email, String password) {
		shopAdminRepository.save(new ShopAdmin(email, password));
	}
}
