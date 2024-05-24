package com.romanticpipe.reviewcanvas.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.romanticpipe.reviewcanvas.entity.BaseEntityWithUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Entity
@Getter
@FieldNameConstants
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Review extends BaseEntityWithUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long id;
	private Long productId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id")
	private User user;
	@OneToMany(mappedBy = "review")
	private List<Reply> replyList;

	private int score;
	private String content;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private ReviewStatus status;
	private String imageVideoUrls;
	private LocalDateTime deletedAt;
	private Integer shopAdminId;

	@Builder
	public Review(Long productId, User user, String content, int score, ReviewStatus status,
		String imageVideoUrls, Integer shopAdminId) {
		this.productId = productId;
		this.user = user;
		this.replyList = new ArrayList<>();
		this.content = content;
		this.score = score;
		this.status = status;
		this.imageVideoUrls = imageVideoUrls;
		this.deletedAt = null;
		this.shopAdminId = shopAdminId;
	}

	public void delete() {
		this.deletedAt = LocalDateTime.now();
	}

	public void update(int score, String content, String savedFileNames) {
		this.score = score;
		this.content = content;
		this.imageVideoUrls = savedFileNames;
	}
}
