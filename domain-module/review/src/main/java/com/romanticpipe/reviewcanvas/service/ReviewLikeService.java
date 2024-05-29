package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.repository.ReviewLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewLikeService {

	private final ReviewLikeRepository reviewLikeRepository;

	public void deleteReviewLike(Long userId, Long reviewId, Integer shopAdminId) {
		reviewLikeRepository.delete(
			reviewLikeRepository.findByReviewIdAndUserIdAndShopAdminId(userId, reviewId, shopAdminId)
				.orElseThrow(() -> new BusinessException(ReviewErrorCode.ALREADY_UNLIKED_REVIEW))
		);
	}
}
