package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import java.time.LocalDateTime;
import java.util.List;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReplyForUserResponse;

public interface ReplyUseCase {

	void createReplyForUser(Long reviewId, CreateReplyRequest createReplyRequest);

	List<GetReplyForUserResponse> getReplyForUser(Long reviewId);

	void createReplyForShopAdmin(Integer shopAdminId, Long reviewId, CreateReplyByShopAdminRequest createReplyByShopAdminRequest);

	void updateReplyForShopAdmin(Integer shopAdminId, Long replyId, UpdateReplyByShopAdminRequest updateReplyByShopAdminRequest);

	void deleteReplyForShopAdmin(Integer shopAdminId, Long replyId, LocalDateTime localDateTime);
}
