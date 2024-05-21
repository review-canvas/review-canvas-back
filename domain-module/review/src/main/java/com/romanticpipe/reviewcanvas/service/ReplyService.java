package com.romanticpipe.reviewcanvas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReplyNotFoundException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.exception.UserNotFoundException;
import com.romanticpipe.reviewcanvas.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	private final ReplyRepository replyRepository;
	private final UserService userService;

	public Reply save(Reply reply) {
		return replyRepository.save(reply);
	}

	public Reply findById(Long replyId) {
		return replyRepository.findById(replyId).orElseThrow(ReplyNotFoundException::new);
	}

	public void validateUpdatable(Reply reply, Optional<User> optionalUser) {
		if (reply.getDeleted())
			throw new BusinessException(ReviewErrorCode.REPLY_CAN_NOT_UPDATE);
		optionalUser.ifPresentOrElse(
			user -> {
				System.out.println("test::::" + user.getId());
				if (!user.getId().equals(reply.getUserId()))
					throw new BusinessException(ReviewErrorCode.WRITER_NOT_MATCH);
			}, () -> {
				throw new UserNotFoundException();
			}
		);
	}
}
