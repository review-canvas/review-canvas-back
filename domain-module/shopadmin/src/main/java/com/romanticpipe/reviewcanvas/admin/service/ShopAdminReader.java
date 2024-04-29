package com.romanticpipe.reviewcanvas.admin.service;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.repository.ShopAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopAdminReader {

	private final ShopAdminRepository shopAdminRepository;


	public List<ShopAdmin> findRegisteredShopAdmin() {
		return shopAdminRepository.findAll();
	}
}
