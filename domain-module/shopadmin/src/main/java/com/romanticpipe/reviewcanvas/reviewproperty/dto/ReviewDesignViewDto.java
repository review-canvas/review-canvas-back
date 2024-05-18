package com.romanticpipe.reviewcanvas.reviewproperty.dto;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.Border;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Font;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Margin;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLike;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Round;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.DetailViewType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.FilterType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.PagingType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.SeeMoreButtonType;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.Shadow;
import lombok.Builder;

@Builder
public record ReviewDesignViewDto(
	DetailViewType detailViewType, PagingType pagingType, FilterType filterType, String filterActiveTextColor,
	String reviewBackgroundColor, Margin margin, Padding padding, String detailInfoTextColor, Font font,
	Border border, Round round, String borderColor, Shadow shadow, String replyBackgroundColor,
	Integer reviewPreviewTextMaxSize, SeeMoreButtonType seeMoreButtonType, ReviewLike reviewLike
) {
}
