package com.romanticpipe.reviewcanvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.DesignItemSuper;

public interface DesignItemSuperRepository extends JpaRepository<DesignItemSuper, Long> {
	DesignItemSuper findByThemeName(String themeName);
}
