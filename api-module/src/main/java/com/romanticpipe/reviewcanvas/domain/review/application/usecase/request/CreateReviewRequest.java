package com.romanticpipe.reviewcanvas.domain.review.application.usecase.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CreateReviewRequest", description = "리뷰 생성 요청")
public record CreateReviewRequest(@Schema(description = "작성자 id", requiredMode = Schema.RequiredMode.REQUIRED)
								  String memberId,
								  @Schema(description = "리뷰 점수", requiredMode = Schema.RequiredMode.REQUIRED)
								  int score,
								  @Schema(description = "리뷰 내용", requiredMode = Schema.RequiredMode.REQUIRED)
								  String content,
								  @Schema(description = "리뷰 이미지 및 동영상", requiredMode = Schema.RequiredMode.REQUIRED)
								  List<MultipartFile> multipartFileList) {
}
