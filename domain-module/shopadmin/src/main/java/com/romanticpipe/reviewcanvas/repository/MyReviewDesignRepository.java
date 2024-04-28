package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.MyReviewDesign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyReviewDesignRepository extends JpaRepository<MyReviewDesign, Integer> {

	// Optional<MyReviewDesign> findByShopAdminIdAndReviewDesignId(Integer shopAdminId, Integer reviewDesignId);
}
