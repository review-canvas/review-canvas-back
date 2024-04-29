package com.romanticpipe.reviewcanvas.admin.repository;

import com.romanticpipe.reviewcanvas.admin.domain.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer> {
	Optional<SuperAdmin> findByEmail(String email);
}
