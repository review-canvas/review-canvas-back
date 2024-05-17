package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReplyRequest;
import com.romanticpipe.reviewcanvas.service.ReplyService;
import com.romanticpipe.reviewcanvas.service.ReviewService;
import com.romanticpipe.reviewcanvas.service.UsersService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReplyUseCaseImpl implements ReplyUseCase {

	private final UsersService usersService;
	private final ReplyService replyService;
	private final ReviewService reviewService;

	@Override
	@Transactional
	public void creteReplyForUser(String mallId, Long productNo, Long reviewId, CreateReplyRequest createReplyRequest) {
		User user = usersService.findUser(createReplyRequest.memberId(), mallId)
			.orElseGet(() -> createUser(mallId, createReplyRequest.memberId()));

		reviewService.validById(reviewId);
		Reply reply = Reply.builder()
			.reviewId(reviewId)
			.userId(user.getId())
			.content(createReplyRequest.content())
			.build();
		replyService.save(reply);
	}

	private User createUser(String mallId, String memberId) {
		return null;
	}
}
