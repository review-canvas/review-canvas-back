package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;
import com.romanticpipe.reviewcanvas.exception.ShopAuthTokenNotFoundException;
import com.romanticpipe.reviewcanvas.repository.ShopAuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
