package com.romanticpipe.reviewcanvas.domain;

import com.romanticpipe.reviewcanvas.entity.BaseEntityWithUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntityWithUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reply_id")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "review_id")
	private Review review;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id")
	private User user;
	private Integer shopAdminId;
	private String content;
	private LocalDateTime deletedAt;

	@Builder
	public Reply(Review review, User user, Integer shopAdminId, String content) {
		this.review = review;
		this.user = user;
		this.shopAdminId = shopAdminId;
		this.content = content;
	}

	public void update(String content) {
		this.content = content;
	}

	public void delete() {
		this.deletedAt = LocalDateTime.now();
	}

	public boolean isThisUserReply(Long userId) {
		return user != null && user.getId().equals(userId);
	}
}
