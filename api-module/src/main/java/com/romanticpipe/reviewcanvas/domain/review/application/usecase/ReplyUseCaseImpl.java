package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.admin.service.ShopAdminService;
import com.romanticpipe.reviewcanvas.config.TransactionUtils;
import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReplyForUserResponse;
import com.romanticpipe.reviewcanvas.service.ReplyService;
import com.romanticpipe.reviewcanvas.service.ReviewService;
import com.romanticpipe.reviewcanvas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReplyUseCaseImpl implements ReplyUseCase {

	private final UserService userService;
	private final ReplyService replyService;
	private final ReviewService reviewService;
	private final ShopAdminService shopAdminService;
	private final UserUseCase userUseCase;
	private final TransactionUtils transactionUtils;

	@Override
	public void createReplyForUser(Long reviewId, CreateReplyRequest createReplyRequest) {
		User user = transactionUtils.executeInReadTransaction(status -> {
			reviewService.validById(reviewId);
			return userService.findUser(createReplyRequest.memberId(), createReplyRequest.mallId());
		}).orElseGet(() ->
			userUseCase.createSaveUser(createReplyRequest.mallId(), createReplyRequest.memberId())
		);

		transactionUtils.executeWithoutResultInWriteTransaction(
			status -> replyService.createAndSave(reviewId, user.getId(), null, createReplyRequest.content())
		);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GetReplyForUserResponse> getReplyForUser(Long reviewId) {
		reviewService.validById(reviewId);
		return replyService.findAllByReviewIdForUser(reviewId)
			.stream()
			.map(
				reply -> GetReplyForUserResponse.from(reply, userService.validateUserByUserId(reply.getUser().getId())))
			.toList();
	}

	@Override
	@Transactional
	public void createReplyForShopAdmin(Integer shopAdminId, Long reviewId,
										CreateReplyByShopAdminRequest createReplyByShopAdminRequest) {
		shopAdminService.validateById(shopAdminId);
		Review review = reviewService.validById(reviewId);

		Reply reply = Reply.builder()
			.content(createReplyByShopAdminRequest.content())
			.shopAdminId(shopAdminId)
			.review(review)
			.build();
		replyService.save(reply);
	}

	@Override
	@Transactional
	public void updateReplyForShopAdmin(Integer shopAdminId, Long replyId,
										UpdateReplyByShopAdminRequest updateReplyByShopAdminRequest) {
		shopAdminService.validateById(shopAdminId);
		Reply reply = replyService.validById(replyId);

		reply.update(updateReplyByShopAdminRequest.content());
	}

	@Override
	@Transactional
	public void deleteReplyForShopAdmin(Integer shopAdminId, Long replyId) {
		shopAdminService.validateById(shopAdminId);
		Reply reply = replyService.validById(replyId);

		reply.delete();
	}

	@Override
	@Transactional
	public void updateReplyForUser(Long replyId, UpdateReplyRequest updateReplyRequest) {
		Reply reply = replyService.validateReplyForUser(replyId);
		User user = userService.validByMemberIdAndMallId(updateReplyRequest.memberId(), updateReplyRequest.mallId());
		replyService.validateUpdateForUser(reply, user);
		reply.update(updateReplyRequest.content());
	}

	@Override
	@Transactional
	public void deleteReplyForUser(String mallId, String memberId, Long replyId) {
		Reply reply = replyService.validateReplyForUser(replyId);
		User user = userService.validByMemberIdAndMallId(memberId, mallId);
		replyService.validateUpdateForUser(reply, user);
		reply.delete();
	}
}
