package com.romanticpipe.reviewcanvas.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminRemover {

	private final ShopAdminValidator shopAdminValidator;

	public void quit(Long id, LocalDateTime time) {
		ShopAdmin shopAdmin = shopAdminValidator.validById(id);
		shopAdmin.delete(time);
	}
}
