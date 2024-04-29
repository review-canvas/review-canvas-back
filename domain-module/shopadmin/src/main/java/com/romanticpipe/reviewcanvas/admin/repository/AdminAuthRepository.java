package com.romanticpipe.reviewcanvas.admin.repository;

import com.romanticpipe.reviewcanvas.admin.domain.AdminAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminAuthRepository extends JpaRepository<AdminAuth, Integer> {
	Optional<AdminAuth> findByShopAdminId(Integer shopAdminId);

	Optional<AdminAuth> findBySuperAdminId(Integer shopAdminId);

	Optional<AdminAuth> findByRefreshToken(String refreshToken);
}
