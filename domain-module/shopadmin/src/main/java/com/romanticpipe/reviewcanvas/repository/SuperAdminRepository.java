package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.SuperAdmin;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
	Optional<SuperAdmin> findByEmail(String email);

	Optional<SuperAdmin> findByAdminAuthId(long adminAuthId);
}
