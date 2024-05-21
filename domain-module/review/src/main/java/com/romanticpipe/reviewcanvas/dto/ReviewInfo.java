package com.romanticpipe.reviewcanvas.dto;

public interface ReviewInfo {
	Long getReviewId();

	String getContent();

	Integer getScore();

	String getMemberId();

	String getNickname();
}
