package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopAdminRepository extends JpaRepository<ShopAdmin, Long> {
	Optional<ShopAdmin> findByEmail(String email);

	Optional<ShopAdmin> findByAdminAuthId(Long adminAuthId);

	boolean existsByEmail(String email);
}
