package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.ShopAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopAdminTokenRepository extends JpaRepository<ShopAuthToken, Integer> {
}
