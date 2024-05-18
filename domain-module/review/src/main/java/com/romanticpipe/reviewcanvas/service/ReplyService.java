package com.romanticpipe.reviewcanvas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.Reply;
import com.romanticpipe.reviewcanvas.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	private final ReplyRepository replyRepository;

	public Reply save(Reply reply) {
		return replyRepository.save(reply);
	}

	public List<Reply> findAllByReviewId(Long reviewId) {
		return replyRepository.findAllByReviewId(reviewId);
	}
}
