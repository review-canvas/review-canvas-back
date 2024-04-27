package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.MyReviewDesign;
import com.romanticpipe.reviewcanvas.domain.ReviewDesign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyReviewDesignRepository extends JpaRepository<MyReviewDesign, Integer> {

	Optional<MyReviewDesign> findByShopAdminIdAndReviewDesignId(Integer shopAdminId, Integer reviewDesignId);

    List<MyReviewDesign> findByShopAdminId(Integer shopAdminId);
}
