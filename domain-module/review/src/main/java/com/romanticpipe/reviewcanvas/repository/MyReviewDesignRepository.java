package com.romanticpipe.reviewcanvas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.MyReviewDesign;
import com.romanticpipe.reviewcanvas.dto.PageResponse;

public interface MyReviewDesignRepository extends JpaRepository<MyReviewDesign, Long> {

	Page<MyReviewDesign> findAllByShopAdminId(long shopAdminId, Pageable pageable);
}
