package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminAuthRepository extends JpaRepository<AdminAuth, Long> {
	Optional<AdminAuth> findById(Long id);
}
