package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewColumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewColumnRepository extends JpaRepository<ReviewColumn, Integer> {
	Optional<ReviewColumn> findByShopAdminId(Integer shopAdminId);
}
