package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.common.util.TransactionUtils;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReplyForUserResponse;
import com.romanticpipe.reviewcanvas.service.ReplyService;
import com.romanticpipe.reviewcanvas.service.ReviewService;
import com.romanticpipe.reviewcanvas.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReplyUseCaseImpl implements ReplyUseCase {

	private final UserService userService;
	private final ReplyService replyService;
	private final ReviewService reviewService;
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
			status -> replyService.createAndSave(reviewId, user.getId(), createReplyRequest.content())
		);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GetReplyForUserResponse> getReplyForUser(Long reviewId) {
		reviewService.validById(reviewId);
		return replyService.findAllByReviewId(reviewId)
			.stream()
			.map(reply -> GetReplyForUserResponse.from(reply, userService.findUserByUserId(reply.getUser().getId())))
			.toList();
	}

	@Override
	public void deleteReplyForUser(String mallId, String memberId, Long replyId, LocalDateTime localDateTime) {

	}
}
