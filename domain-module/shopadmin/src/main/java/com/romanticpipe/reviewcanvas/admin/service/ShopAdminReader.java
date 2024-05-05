package com.romanticpipe.reviewcanvas.admin.service;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.repository.ShopAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopAdminReader {

	private final ShopAdminRepository shopAdminRepository;


	public List<ShopAdmin> findAll() {
		return shopAdminRepository.findAll();
	}

	public Optional<ShopAdmin> findByMallId(String mallId) {
		return shopAdminRepository.findByMallId(mallId);
	}
}
