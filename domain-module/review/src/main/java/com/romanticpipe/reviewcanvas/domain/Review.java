package com.romanticpipe.reviewcanvas.domain;

import com.romanticpipe.reviewcanvas.entity.BaseEntityWithUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Review extends BaseEntityWithUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long id;

	@Column(name = "product_id")
	private String productId;

	private String userId;
	private int score;
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private ReviewStatus status;

	public Review(String productId, String userId, String content, int score, ReviewStatus status) {
		this.userId = userId;
		this.productId = productId;
		this.content = content;
		this.score = score;
		this.status = status;
	}
}
