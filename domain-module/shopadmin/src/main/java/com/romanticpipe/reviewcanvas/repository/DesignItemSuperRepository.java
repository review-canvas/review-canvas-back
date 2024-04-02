package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.DesignItemSuper;

public interface DesignItemSuperRepository extends JpaRepository<DesignItemSuper, Long> {
	Optional<DesignItemSuper> findByThemeName(String themeName);
}
