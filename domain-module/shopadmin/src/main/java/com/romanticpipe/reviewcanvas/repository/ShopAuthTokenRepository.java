package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopAuthTokenRepository extends JpaRepository<ShopAuthToken, Integer> {
	Optional<ShopAuthToken> findByMallId(String mallId);
}
