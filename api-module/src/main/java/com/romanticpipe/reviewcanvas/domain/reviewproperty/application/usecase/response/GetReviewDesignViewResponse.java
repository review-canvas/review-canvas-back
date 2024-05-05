package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.response;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Font;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Margin;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewDesignView;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLike;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Round;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Shadow;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
@Schema(description = "리뷰 디자인-리뷰 보기")
public record GetReviewDesignViewResponse(
	@Schema(description = "리뷰 자세히 보기 방식", requiredMode = Schema.RequiredMode.REQUIRED,
		allowableValues = {"SPREAD", "MODAL"})
	String detailViewType,
	@Schema(description = "페이징 방식", requiredMode = Schema.RequiredMode.REQUIRED,
		allowableValues = {"PAGE_NUMBER", "SEE_MORE_SCROLL"})
	String pagingType,
	@Schema(description = "리뷰 필터", requiredMode = Schema.RequiredMode.REQUIRED,
		allowableValues = {"LIST", "DROPDOWN"})
	String filterType,
	@Schema(description = "필터 활성화 텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#000000")
	String filterActiveTextColor,
	@Schema(description = "각 리뷰 영역 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#FFFFFF")
	String reviewBackgroundColor,
	@Schema(description = "바깥쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	Margin margin,
	@Schema(description = "안쪽 여백", requiredMode = Schema.RequiredMode.REQUIRED)
	Padding padding,
	@Schema(description = "상세 정보 텍스트 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#000000")
	String detailInfoTextColor,
	@Schema(description = "리뷰 텍스트", requiredMode = Schema.RequiredMode.REQUIRED)
	Font font,
	@Schema(description = "리뷰 테두리", requiredMode = Schema.RequiredMode.REQUIRED)
	Border border,
	@Schema(description = "라운드", requiredMode = Schema.RequiredMode.REQUIRED)
	Round round,
	@Schema(description = "테두리 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#000000")
	String borderColor,
	@Schema(description = "그림자", requiredMode = Schema.RequiredMode.REQUIRED)
	Shadow shadow,
	@Schema(description = "답글 배경 색상", requiredMode = Schema.RequiredMode.REQUIRED, example = "#FFFFFF")
	String replyBackgroundColor,
	@Schema(description = "리뷰 미리보기 최대 글자 수", requiredMode = Schema.RequiredMode.REQUIRED)
	Integer reviewPreviewTextMaxSize,
	@Schema(description = "더보기 버튼 스타일", requiredMode = Schema.RequiredMode.REQUIRED)
	String seeMoreButtonType,
	@Schema(description = "리뷰 좋아요", requiredMode = Schema.RequiredMode.REQUIRED)
	ReviewLike reviewLike
) {
	public static GetReviewDesignViewResponse from(ReviewDesignView reviewDesignView) {
		return GetReviewDesignViewResponse.builder()
			.detailViewType(reviewDesignView.getDetailViewType())
			.pagingType(reviewDesignView.getPagingType())
			.filterType(reviewDesignView.getFilterType())
			.filterActiveTextColor(reviewDesignView.getFilterActiveTextColor())
			.reviewBackgroundColor(reviewDesignView.getReviewBackgroundColor())
			.margin(reviewDesignView.getMargin())
			.padding(reviewDesignView.getPadding())
			.detailInfoTextColor(reviewDesignView.getDetailInfoTextColor())
			.font(reviewDesignView.getFont())
			.border(reviewDesignView.getBorder())
			.round(reviewDesignView.getRound())
			.borderColor(reviewDesignView.getBorderColor())
			.shadow(reviewDesignView.getShadow())
			.replyBackgroundColor(reviewDesignView.getReplyBackgroundColor())
			.reviewPreviewTextMaxSize(reviewDesignView.getReviewPreviewTextMaxSize())
			.seeMoreButtonType(reviewDesignView.getSeeMoreButtonType())
			.reviewLike(reviewDesignView.getReviewLike())
			.build();
	}
}
