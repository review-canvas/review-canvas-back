package com.romanticpipe.reviewcanvas.domain;

import com.romanticpipe.reviewcanvas.entity.BaseEntityWithUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Getter
@Setter
@FieldNameConstants
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Review extends BaseEntityWithUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long id;
	private Long productId;
	private Long userId;

	private int score;
	private String content;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private ReviewStatus status;
	private String imageVideoUrls;

	@Builder
	public Review(Long productId, Long userId, String content, int score, ReviewStatus status, String imageVideoUrls) {
		this.userId = userId;
		this.productId = productId;
		this.content = content;
		this.score = score;
		this.status = status;
	}
}
