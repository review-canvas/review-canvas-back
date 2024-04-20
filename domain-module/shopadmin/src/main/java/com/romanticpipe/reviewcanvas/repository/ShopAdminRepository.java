package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopAdminRepository extends JpaRepository<ShopAdmin, Integer> {
	Optional<ShopAdmin> findByEmail(String email);

	boolean existsByEmail(String email);
}
