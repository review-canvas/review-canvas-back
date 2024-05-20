package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
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
	private final TransactionTemplate readTransactionTemplate;
	private final TransactionTemplate writeTransactionTemplate;

	@Override
	public void createReplyForUser(Long reviewId, CreateReplyRequest createReplyRequest) {
		//TODO: sonarCloud 정적분석 통과 안된 코드
		// Optional<User> optionalUser = readTransactionTemplate.execute(status -> {
		// 	reviewService.validById(reviewId);
		// 	return userService.findUser(createReplyRequest.memberId(), createReplyRequest.mallId());
		// });
		// User user = optionalUser.orElseGet(
		// 	() -> userUseCase.createSaveUser(createReplyRequest.mallId(), createReplyRequest.memberId())
		// );

		User user = userService.findUser(createReplyRequest.memberId(), createReplyRequest.mallId()).orElseGet(
			() -> userUseCase.createSaveUser(createReplyRequest.mallId(), createReplyRequest.memberId())
		);

		writeTransactionTemplate.executeWithoutResult(status -> {
			try {
				Reply reply = Reply.builder()
					.reviewId(reviewId)
					.userId(user.getId())
					.content(createReplyRequest.content())
					.build();
				replyService.save(reply);
			} catch (RuntimeException e) {
				status.setRollbackOnly();
				throw e;
			}
		});
	}
}
