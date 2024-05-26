package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import java.util.List;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReplyForUserResponse;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyRequest;

public interface ReplyUseCase {

	void createReplyForUser(Long reviewId, CreateReplyRequest createReplyRequest);

	List<GetReplyForUserResponse> getReplyForUser(Long reviewId);

	void updateReplyForUser(Long replyId, UpdateReplyRequest updateReplyRequest);
}
