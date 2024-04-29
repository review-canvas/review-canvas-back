package com.romanticpipe.reviewcanvas.admin.service;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.repository.ShopAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopAdminCreator {
	private final ShopAdminRepository shopAdminRepository;

	public ShopAdmin save(ShopAdmin shopAdmin) {
		return shopAdminRepository.save(shopAdmin);
	}
}
