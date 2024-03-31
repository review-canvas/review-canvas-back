package com.romanticpipe.reviewcanvas.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.repository.ReviewRepository;
import com.romanticpipe.reviewcanvas.util.PageableUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewReader {

	private final ReviewRepository reviewRepository;

	public PageResponse<Review> findByProductId(String productId, PageableRequest pageableRequest) {
		Pageable pageable = PageableUtils.toPageable(pageableRequest);
		return PageableUtils.toPageResponse(reviewRepository.findAllByProductId(productId, pageable));
	}

	public PageResponse<Review> findAllByisChecked(String productId, PageableRequest pageableRequest) {
		Pageable pageable = PageableUtils.toPageable(pageableRequest);
		return PageableUtils.toPageResponse(
			reviewRepository.findAllByProductIdAndIsChecked(productId, false, pageable));
	}

}
