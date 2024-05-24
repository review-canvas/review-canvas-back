package com.romanticpipe.reviewcanvas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReplyNotFoundException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.repository.ReplyRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final EntityManager entityManager;
	private final UserService userService;

	public Reply save(Reply reply) {
		return replyRepository.save(reply);
	}

	public void createAndSave(Long reviewId, Long userId, String content) {
		Reply reply = Reply.builder()
			.review(entityManager.getReference(Review.class, reviewId))
			.user(entityManager.getReference(User.class, userId))
			.content(content)
			.build();
		replyRepository.save(reply);
	}

	public List<Reply> findAllByReviewId(Long reviewId) {
		return replyRepository.findAllByReviewId(reviewId);
	}

	public Reply validateReplyForUser(Long replyId) {
		return replyRepository.findById(replyId).orElseThrow(ReplyNotFoundException::new);
	}

	public void validateUpdateForUser(Reply reply, User user) {
		if (reply.getDeletedAt() != null) {
			throw new ReplyNotFoundException();
		}
		if (reply.getUser() == null || !user.getId().equals(reply.getUser().getId())) {
			throw new BusinessException(ReviewErrorCode.WRITER_NOT_MATCH);
		}
	}
}
