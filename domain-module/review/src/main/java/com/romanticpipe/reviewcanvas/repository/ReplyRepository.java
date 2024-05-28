package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	List<Reply> findAllByReviewIdAndUserIdIsNotNull(Long reviewId);

	List<Reply> findAllByReviewIdAndDeletedAtIsNull(Long reviewId);
}
