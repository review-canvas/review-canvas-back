package com.romanticpipe.reviewcanvas.admin.repository;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopAuthTokenRepository extends JpaRepository<ShopAuthToken, Integer> {
	Optional<ShopAuthToken> findByMallId(String mallId);
}
