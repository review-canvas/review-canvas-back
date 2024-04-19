package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.Product;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;
import com.romanticpipe.reviewcanvas.util.PageableUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewValidator {

	private final ReviewRepository reviewRepository;

	public Review validById(long reviewId) {
		Optional<Review> reviewOptional = Optional.ofNullable(reviewRepository.findById(reviewId));
		if (reviewOptional.isEmpty()) {
			throw new BusinessException(ReviewErrorCode.REVIEW_NOT_FOUND);
		}
		return reviewOptional.get();
	}

	public PageResponse<Review> validAwaitReviewByShopAdminId(long shopAdminId, PageableRequest pageableRequest) {
		Pageable pageable = PageableUtils.toPageable(pageableRequest);
		return PageableUtils.toPageResponse(reviewRepository.findAllByShopAdminId(shopAdminId, pageable));
	}

}
