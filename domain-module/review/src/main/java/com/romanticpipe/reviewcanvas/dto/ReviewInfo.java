package com.romanticpipe.reviewcanvas.dto;

public interface ReviewInfo {
	Long getReviewId();

	String getContent();

	Integer getScore();

	Long getUserId();

	String getNickname();
}
