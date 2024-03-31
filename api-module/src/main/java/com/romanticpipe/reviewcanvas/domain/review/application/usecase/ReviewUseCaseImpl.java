package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.service.ReplyCreater;
import com.romanticpipe.reviewcanvas.service.ReviewReader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ReviewUseCaseImpl implements ReviewUseCase {

	private final ReviewReader reviewReader;
	private final ReplyCreater replyCreater;

	@Override
	@Transactional(readOnly = true)
	public PageResponse<GetReviewResponse> getReviews(String productId, PageableRequest pageableRequest) {
		return reviewReader.findByProductId(productId, pageableRequest)
			.map(GetReviewResponse::from);
	}

	@Override
	@Transactional
	public Long createReply(Long reviewId, String userId, String content) {
		return replyCreater.createReply(reviewId, userId, content).getId();
	}
}
