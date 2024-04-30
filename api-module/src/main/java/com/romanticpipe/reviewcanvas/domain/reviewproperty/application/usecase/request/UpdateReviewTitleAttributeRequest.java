package com.romanticpipe.reviewcanvas.domain.reviewproperty.application.usecase.request;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.AlignmentPosition;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Boarder;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Font;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.Padding;

import lombok.Getter;

@Getter
public class UpdateReviewTitleAttributeRequest {
	private String title;
	private AlignmentPosition titleAlignmentPosition;
	private Padding titlePadding;
	private Font titleFont;
	private Boarder titleBoarder;
	private String titleBackGround;
	private String description;
	private AlignmentPosition descriptionAlignmentPosition;
	private Padding descriptionPadding;
	private Font descriptionFont;
	private Boarder descriptionBoarder;
	private String descriptionBackGround;

}
