package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;

public interface ReplyUseCase {

	void creteReplyForUser(String mallId, Long productNo, Long reviewId, CreateReplyRequest createReplyRequest);
}
