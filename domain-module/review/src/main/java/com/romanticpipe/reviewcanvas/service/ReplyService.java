package com.romanticpipe.reviewcanvas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReplyErrorCode;
import com.romanticpipe.reviewcanvas.exception.ReplyNotFoundException;
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

	public void validateUserIsWriter(Reply reply, Optional<User> optionalUser) {
		optionalUser.ifPresentOrElse(
			user -> {
				if (user.getId() != reply.getUserId())
					throw new BusinessException(ReplyErrorCode.WRITER_NOT_MATCH);
			}, () -> {
				throw new BusinessException(ReplyErrorCode.WRITER_NOT_MATCH);
			}
		);
	}
}
