package com.romanticpipe.reviewcanvas.admin.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.admin.domain.ShopAdmin;

public interface ShopAdminRepository extends JpaRepository<ShopAdmin, Integer> {
	Optional<ShopAdmin> findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<ShopAdmin> findByMallId(String mallId);

	Page<ShopAdmin> findAll(Pageable pageable);
}
