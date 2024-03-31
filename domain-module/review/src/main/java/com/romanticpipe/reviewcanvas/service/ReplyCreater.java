package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyCreater {

	private final ReplyRepository replyRepository;

	public Reply createReply(Long reviewId, String userId, String content) {
		return replyRepository.save(new Reply(reviewId, userId, content));
	}
}
