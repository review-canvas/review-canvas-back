package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;
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
