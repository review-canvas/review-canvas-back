package com.romanticpipe.reviewcanvas.domain;

import com.romanticpipe.reviewcanvas.entity.BaseEntityWithUpdate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
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
	@NotNull
	private Long reviewId;
	@NotNull
	private Long userId;
	@NotBlank
	private String content;

	public Reply(Long reviewId, Long userId, String content) {
		this.reviewId = reviewId;
		this.userId = userId;
		this.content = content;
	}
}
