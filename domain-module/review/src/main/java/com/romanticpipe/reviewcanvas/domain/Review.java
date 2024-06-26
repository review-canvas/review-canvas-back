package com.romanticpipe.reviewcanvas.domain;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@FieldNameConstants
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Review extends BaseEntityWithUpdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id")
	private User user;
	private Integer shopAdminId;
	@OneToMany(mappedBy = "review")
	private List<Reply> replyList;

	private int score;
	private String content;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private ReviewStatus status;
	private String imageVideoUrls;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private ReviewType reviewType;
	private LocalDateTime deletedAt;

	@Builder
	public Review(Product product, User user, String content, int score, ReviewStatus status,
				  String imageVideoUrls, ReviewType reviewType, Integer shopAdminId) {
		this.product = product;
		this.user = user;
		this.replyList = new ArrayList<>();
		this.content = content;
		this.score = score;
		this.status = status;
		this.imageVideoUrls = imageVideoUrls;
		this.reviewType = reviewType;
		this.deletedAt = null;
		this.shopAdminId = shopAdminId;
	}

	public void delete() {
		this.deletedAt = LocalDateTime.now();
	}

	public void update(int score, String content, String imageVideoUrls, ReviewType reviewType) {
		this.score = score;
		this.content = content;
		this.imageVideoUrls = imageVideoUrls;
		this.reviewType = reviewType;
	}

	public boolean isThisShopReview(Integer shopAdminId) {
		return Objects.equals(product.getShopAdminId(), shopAdminId);
	}

	public boolean isThisUserReview(String mallId, String memberId) {
		return user != null && Objects.equals(user.getMallId(), mallId) && Objects.equals(user.getMemberId(), memberId);
	}

	public boolean isThisUserReview(Long userId) {
		return user != null && Objects.equals(user.getId(), userId);
	}

	public boolean isShopAdminReview() {
		return shopAdminId != null;
	}
}
