package com.romanticpipe.reviewcanvas.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.exception.AdminNotFoundException;
import com.romanticpipe.reviewcanvas.admin.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.admin.repository.ShopAdminRepository;
import com.romanticpipe.reviewcanvas.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminService {

	private final ShopAdminRepository shopAdminRepository;

	public ShopAdmin save(ShopAdmin shopAdmin) {
		return shopAdminRepository.save(shopAdmin);
	}

	public List<ShopAdmin> findAll() {
		return shopAdminRepository.findAll();
	}

	public Optional<ShopAdmin> findByMallId(String mallId) {
		return shopAdminRepository.findByMallId(mallId);
	}

	public ShopAdmin validByEmail(String email) {
		return shopAdminRepository.findByEmail(email)
			.orElseThrow(() -> new AdminNotFoundException());
	}

	public ShopAdmin validById(Integer shopAdminId) {
		return shopAdminRepository.findById(shopAdminId)
			.orElseThrow(AdminNotFoundException::new);
	}

	public boolean isExistEmail(String email) {
		return shopAdminRepository.existsByEmail(email);
	}

	public ShopAdmin validByMallId(String mallId) {
		return shopAdminRepository.findByMallId(mallId)
			.orElseThrow(AdminNotFoundException::new);
	}

	public void validateEmailDuplicated(String email) {
		shopAdminRepository.findByEmail(email)
			.ifPresent(admin -> {
				throw new BusinessException(ShopAdminErrorCode.DUPLICATED_EMAIL);
			});
	}
}
