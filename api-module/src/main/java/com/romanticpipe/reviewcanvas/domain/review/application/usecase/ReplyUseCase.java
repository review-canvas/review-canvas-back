package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import java.time.LocalDateTime;
import java.util.List;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReplyForUserResponse;

public interface ReplyUseCase {

	void createReplyForUser(Long reviewId, CreateReplyRequest createReplyRequest);

	List<GetReplyForUserResponse> getReplyForUser(Long reviewId);

	void deleteReplyForUser(String mallId, String memberId, Long replyId, LocalDateTime localDateTime);
}
