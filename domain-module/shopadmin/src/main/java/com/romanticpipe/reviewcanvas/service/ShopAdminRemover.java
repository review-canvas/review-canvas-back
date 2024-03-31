package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.ShopAdminNotFoundException;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminRemover {
	private final ShopAdminRepository shopAdminRepository;

	public void quit(Long id) {
		ShopAdmin shopAdmin = shopAdminRepository.findById(id).orElseThrow(() -> new ShopAdminNotFoundException());
		shopAdminRepository.delete(shopAdmin);
	}
}
