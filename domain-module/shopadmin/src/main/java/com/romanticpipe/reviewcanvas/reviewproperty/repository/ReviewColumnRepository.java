package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;

public interface ReviewColumnRepository extends JpaRepository<ReviewColumn, Integer> {
	Optional<ReviewColumn> findByShopAdminId(Integer shopAdminId);
}
