package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReplyForUserResponse;

import java.util.List;

public interface ReplyUseCase {

	void createReplyForUser(Long reviewId, CreateReplyRequest createReplyRequest);

	List<GetReplyForUserResponse> getReplyForUser(Long reviewId);

	void updateReplyForUser(Long replyId, UpdateReplyRequest updateReplyRequest);

	void deleteReplyForUser(String mallId, String memberId, Long replyId);

	void createReplyForShopAdmin(Integer shopAdminId, Long reviewId,
								 CreateReplyByShopAdminRequest createReplyByShopAdminRequest);

	void updateReplyForShopAdmin(Integer shopAdminId, Long replyId,
								 UpdateReplyByShopAdminRequest updateReplyByShopAdminRequest);

	void deleteReplyForShopAdmin(Integer shopAdminId, Long replyId);
}
