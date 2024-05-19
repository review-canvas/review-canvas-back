package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.DetailViewType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FilterType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.PagingType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.SeeMoreButtonType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.Shadow;
import com.romanticpipe.reviewcanvas.reviewproperty.dto.ReviewDesignViewDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(name = "UpdateDesignViewRequest", description = "리뷰 디자인 보기 수정 api용 dto")
public record UpdateDesignViewRequest(
	@Schema(description = "리뷰 자세히 보기 방식", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull DetailViewType detailViewType,
	@Schema(description = "페이징 방식", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull PagingType pagingType,
	@Schema(description = "리뷰 필터", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull FilterType filterType,
	@Schema(description = "필터 활성화 텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#000000")
	@NotBlank String filterActiveTextColor,
	@Schema(description = "각 리뷰 영역 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#FFFFFF")
	@NotBlank String reviewBackgroundColor,
	@Schema(description = "바깥쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid MarginRequest margin,
	@Schema(description = "안쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid PaddingRequest padding,
	@Schema(description = "상세 정보 텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#000000")
	@NotBlank String detailInfoTextColor,
	@Schema(description = "리뷰 텍스트", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid FontRequest font,
	@Schema(description = "리뷰 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid BorderRequest border,
	@Schema(description = "라운드", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid RoundRequest round,
	@Schema(description = "테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#000000")
	@NotBlank String borderColor,
	@Schema(description = "그림자", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull Shadow shadow,
	@Schema(description = "답글 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#FFFFFF")
	@NotBlank String replyBackgroundColor,
	@Schema(description = "리뷰 미리보기 최대 글자 수", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull @Positive Integer reviewPreviewTextMaxSize,
	@Schema(description = "더보기 버튼 스타일", requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull SeeMoreButtonType seeMoreButtonType,
	@Schema(description = "리뷰 좋아요", requiredMode = Schema.RequiredMode.REQUIRED)
	@Valid ReviewLikeRequest reviewLike
) {

	public ReviewDesignViewDto toDto() {
		return ReviewDesignViewDto.builder()
			.detailViewType(detailViewType)
			.pagingType(pagingType)
			.filterType(filterType)
			.filterActiveTextColor(filterActiveTextColor)
			.reviewBackgroundColor(reviewBackgroundColor)
			.margin(margin.toVO())
			.padding(padding.toVO())
			.detailInfoTextColor(detailInfoTextColor)
			.font(font.toVO())
			.border(border.toVO())
			.round(round.toVO())
			.borderColor(borderColor)
			.shadow(shadow)
			.replyBackgroundColor(replyBackgroundColor)
			.reviewPreviewTextMaxSize(reviewPreviewTextMaxSize)
			.seeMoreButtonType(seeMoreButtonType)
			.reviewLike(reviewLike.toVO())
			.build();
	}
}
