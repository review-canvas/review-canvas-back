package com.romanticpipe.reviewcanvas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	List<Reply> findAllByReviewId(Long reviewId);

}
