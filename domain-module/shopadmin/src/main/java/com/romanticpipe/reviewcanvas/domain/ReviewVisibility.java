package com.romanticpipe.reviewcanvas.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class ReviewVisibility {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_visibility_id")
	private Integer id;
	private Integer shopAdminId;
	private Boolean title;
	private Boolean author;
	private Boolean point;
	private Boolean media;
	private Boolean content;
	private Boolean createdAt;
	private Boolean updatedAt;

	@Builder
	public ReviewVisibility(Integer shopAdminId, Boolean title, Boolean author, Boolean point, Boolean media,
							Boolean content, Boolean createdAt, Boolean updatedAt) {
		this.shopAdminId = shopAdminId;
		this.title = title;
		this.author = author;
		this.point = point;
		this.media = media;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

}
