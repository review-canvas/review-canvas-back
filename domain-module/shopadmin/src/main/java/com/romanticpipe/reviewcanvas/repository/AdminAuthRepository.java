package com.romanticpipe.reviewcanvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.AdminAuth;

public interface AdminAuthRepository extends JpaRepository<AdminAuth, Long> {
}
