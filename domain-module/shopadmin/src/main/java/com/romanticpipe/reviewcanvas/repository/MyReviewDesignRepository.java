package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.MyReviewDesign;

public interface MyReviewDesignRepository extends JpaRepository<MyReviewDesign, Integer> {

	Optional<MyReviewDesign> findByShopAdminIdAndReviewListDesignId(Integer shopAdminId, Integer reviewDesignId);
}
