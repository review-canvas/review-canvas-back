package com.romanticpipe.reviewcanvas.domain.shop.application.usecase.request;

import com.romanticpipe.reviewcanvas.cafe24.application.Cafe24CreateScriptTagDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Schema(description = "카페24 스크립트 태그 생성 API request")
public record Cafe24CreateScriptTagRequest(
	@Schema(description = "설치할 스크립트의 원본 경로(절대 경로)",
		requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank String src,
	@Schema(description = "스크립트를 표시할 화면 경로. 화면 경로는 화면의 페이지 경로가 아니라 쇼핑몰의 각 페이지에 부여된 특정한 역할을 의미함",
		requiredMode = Schema.RequiredMode.REQUIRED)
	@NotEmpty
	List<String> displayLocation,
	@Schema(description = "제외 경로", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	List<String> excludeLocation,
	@Schema(description = "스크립트를 적용할 스킨 번호", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	List<Integer> skinNo
) {
	public Cafe24CreateScriptTagDto toCafe24CreateScriptTagDto() {
		return Cafe24CreateScriptTagDto.of(src, displayLocation, excludeLocation, skinNo);
	}
}
