package com.romanticpipe.reviewcanvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.ShopAdmin;

public interface ShopAdminRepository extends JpaRepository<ShopAdmin, Long> {
	ShopAdmin findByEmail(String email);
}
