package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;
import com.romanticpipe.reviewcanvas.repository.ShopAdminTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopAdminTokenCreator {

	private final ShopAdminTokenRepository shopAdminTokenRepository;

	public ShopAuthToken save(ShopAuthToken shopAuthToken) {
		return shopAdminTokenRepository.save(shopAuthToken);
	}
}
