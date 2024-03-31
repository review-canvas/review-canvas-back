package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reply_id")
	private Long id;

	@Column(name = "review_id")
	private Long reviewId;

	@Column(name = "user_id")
	private String userId;
	private String content;

	public Reply(Long reviewId, String userId, String content) {
		this.reviewId = reviewId;
		this.userId = userId;
		this.content = content;
	}
}
