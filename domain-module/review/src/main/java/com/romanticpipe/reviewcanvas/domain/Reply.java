package com.romanticpipe.reviewcanvas.domain;

import com.romanticpipe.reviewcanvas.entity.BaseEntityWithUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntityWithUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reply_id")
	private Long id;
	private Long reviewId;
	@Column(name = "users_id")
	private Long userId;
	private String content;
	private Boolean deleted;

	@Builder
	public Reply(Long reviewId, Long userId, String content, Boolean deleted) {
		this.reviewId = reviewId;
		this.userId = userId;
		this.content = content;
		this.deleted = deleted;
	}

	public void update(String content, Boolean deleted) {
		this.content = content;
		this.deleted = deleted;
	}
}
