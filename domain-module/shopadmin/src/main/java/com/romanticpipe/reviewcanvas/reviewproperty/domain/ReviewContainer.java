package com.romanticpipe.reviewcanvas.reviewproperty.domain;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.Shadow;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.romanticpipe.reviewcanvas.enumeration.Color.WHITE;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewContainer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_container_id")
	private Integer id;

	private String width;
	@Embedded
	private Padding padding;
	private String background;
	@Embedded
	private Border border;
	private String borderColor;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR")
	private Shadow shadow;
	private Integer shopAdminId;

	@Builder(access = AccessLevel.PRIVATE)
	private ReviewContainer(String width, Padding padding, String background, Border border, String borderColor,
							Shadow shadow,
							Integer shopAdminId) {
		this.width = width;
		this.padding = padding;
		this.background = background;
		this.border = border;
		this.borderColor = borderColor;
		this.shadow = shadow;
		this.shopAdminId = shopAdminId;
	}

	public static ReviewContainer create(Integer shopAdminId) {
		return ReviewContainer.builder()
			.width("100%")
			.padding(Padding.createDefaultReviewContainer())
			.background(WHITE.getHex())
			.border(Border.createDefaultReviewContainer())
			.borderColor(WHITE.getHex())
			.shadow(Shadow.NONE)
			.shopAdminId(shopAdminId)
			.build();
	}

	public void update(String width, Padding padding, String background, Border border, String borderColor,
					   Shadow shadow) {
		this.width = width;
		this.padding = padding;
		this.background = background;
		this.border = border;
		this.borderColor = borderColor;
		this.shadow = shadow;
	}

	public void reset() {
		this.width = "100%";
		this.padding = Padding.createDefaultReviewContainer();
		this.background = WHITE.getHex();
		this.border = Border.createDefaultReviewContainer();
		this.borderColor = WHITE.getHex();
		this.shadow = Shadow.NONE;
	}
}
