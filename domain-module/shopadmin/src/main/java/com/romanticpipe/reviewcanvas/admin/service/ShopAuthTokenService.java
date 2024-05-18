package com.romanticpipe.reviewcanvas.admin.service;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAuthToken;
import com.romanticpipe.reviewcanvas.admin.exception.ShopAuthTokenNotFoundException;
import com.romanticpipe.reviewcanvas.admin.repository.ShopAuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopAuthTokenService {

	private final ShopAuthTokenRepository shopAuthTokenRepository;

	public ShopAuthToken save(ShopAuthToken shopAuthToken) {
		return shopAuthTokenRepository.save(shopAuthToken);
	}

	public ShopAuthToken validateByMallId(String mallId) {
		return shopAuthTokenRepository.findByMallId(mallId)
			.orElseThrow(ShopAuthTokenNotFoundException::new);
	}

	public Optional<ShopAuthToken> findByMallId(String mallId) {
		return shopAuthTokenRepository.findByMallId(mallId);
	}

	public List<ShopAuthToken> findAll() {
		return shopAuthTokenRepository.findAll();
	}
}
