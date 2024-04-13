package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;

public interface ShopAdminRepository extends JpaRepository<ShopAdmin, Long> {
	Optional<ShopAdmin> findByEmail(String email);

	boolean existsByEmail(String email);
}
