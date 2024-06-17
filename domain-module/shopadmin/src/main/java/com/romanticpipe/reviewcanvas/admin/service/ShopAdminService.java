package com.romanticpipe.reviewcanvas.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.admin.domain.AdminRole;
import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.admin.exception.AdminNotFoundException;
import com.romanticpipe.reviewcanvas.admin.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.admin.repository.ShopAdminRepository;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.CommonErrorCode;
import com.romanticpipe.reviewcanvas.util.PageableUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminService {

	private final ShopAdminRepository shopAdminRepository;
	private final SuperAdminService superAdminService;

	public ShopAdmin save(ShopAdmin shopAdmin) {
		return shopAdminRepository.save(shopAdmin);
	}

	public PageResponse<ShopAdmin> findAll(PageableRequest pageableRequest) {
		return PageableUtils.toPageResponse(shopAdminRepository.findAll(PageableUtils.toPageable(pageableRequest)));
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

	public ShopAdmin validateById(Integer shopAdminId) {
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

	public void validateIsMyIdOrSuperAdmin(Integer adminId, AdminRole adminRole, Integer loginAdminId) {
		if (adminRole == AdminRole.ROLE_SUPER_ADMIN) {
			superAdminService.validById(loginAdminId);
			return;
		}

		if (!adminId.equals(loginAdminId)) {
			throw new BusinessException(CommonErrorCode.UNAUTHORIZED);
		}

		this.validateById(loginAdminId);
	}
}
