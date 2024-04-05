package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminRemover {

	private final ShopAdminValidator shopAdminValidator;

	public void quit(Long id) {
		ShopAdmin shopAdmin = shopAdminValidator.isExistUserByID(id);
		shopAdmin.delete();
	}
}
