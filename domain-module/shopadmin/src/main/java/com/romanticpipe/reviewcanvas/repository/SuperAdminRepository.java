package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Integer> {
	Optional<SuperAdmin> findByEmail(String email);
}
