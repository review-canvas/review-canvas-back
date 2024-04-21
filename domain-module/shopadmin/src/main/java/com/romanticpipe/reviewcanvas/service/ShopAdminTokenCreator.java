package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;
import com.romanticpipe.reviewcanvas.repository.ShopAuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopAdminTokenCreator {

	private final ShopAuthTokenRepository shopAuthTokenRepository;

	public ShopAuthToken save(ShopAuthToken shopAuthToken) {
		return shopAuthTokenRepository.save(shopAuthToken);
	}
}
