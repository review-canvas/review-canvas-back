package com.romanticpipe.reviewcanvas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import com.romanticpipe.reviewcanvas.exception.AdminNotFoundException;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewDesignNotFoundException;
import com.romanticpipe.reviewcanvas.exception.ShopAdminErrorCode;
import com.romanticpipe.reviewcanvas.repository.ReviewDesignRepository;
import com.romanticpipe.reviewcanvas.repository.ShopAdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopAdminReader {
	private final ShopAdminRepository shopAdminRepository;

	public Optional<ShopAdmin> findById(Integer shopAdminId) {
		return shopAdminRepository.findById(shopAdminId);
	}

}
