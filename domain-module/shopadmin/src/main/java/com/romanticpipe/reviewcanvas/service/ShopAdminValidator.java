package com.romanticpipe.reviewcanvas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.DesignItemSuper;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.DesignItemErrorCode;
import com.romanticpipe.reviewcanvas.exception.ShopAdminNotFoundException;
import com.romanticpipe.reviewcanvas.repository.DesignItemSuperRepository;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminValidator {
	private final ShopAdminRepository shopAdminRepository;
	private final DesignItemSuperRepository designItemSuperRepository;

	public ShopAdmin login(String email, String password) {
		Optional<ShopAdmin> shopAdmin = shopAdminRepository.findByEmail(email);
		if (shopAdmin.isPresent() && shopAdmin.get().getPassword().equals(password)) {
			return shopAdmin.get();
		} else {
			throw new ShopAdminNotFoundException();
		}
	}

	public DesignItemSuper isExistTheme(String themeName) {
		return designItemSuperRepository.findByThemeName(themeName)
			.orElseThrow(() -> new BusinessException(DesignItemErrorCode.THEME_NOT_FOUND));
	}

	public ShopAdmin isExsitUser(String email) {
		return shopAdminRepository.findByEmail(email)
			.orElseThrow(() -> new ShopAdminNotFoundException());
	}
}
