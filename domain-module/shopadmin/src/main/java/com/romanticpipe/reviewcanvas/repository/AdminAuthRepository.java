package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;

public interface AdminAuthRepository extends JpaRepository<AdminAuth, Long> {
	Optional<AdminAuth> findByShopAdminId(Long shopAdminId);
}
