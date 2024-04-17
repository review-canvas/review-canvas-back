package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopAdminCreator {
	private final ShopAdminRepository shopAdminRepository;

	public ShopAdmin signUp(ShopAdmin shopAdmin) {
		return shopAdminRepository.save(shopAdmin);
	}
}
