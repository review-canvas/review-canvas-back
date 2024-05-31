package com.romanticpipe.reviewcanvas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.ReviewLike;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
	Integer countAllByReviewId(Long reviewId);
}
