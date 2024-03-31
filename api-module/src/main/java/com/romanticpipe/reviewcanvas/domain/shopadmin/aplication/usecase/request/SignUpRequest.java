package com.romanticpipe.reviewcanvas.domain.shopadmin.aplication.usecase.request;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SignUpRequest", description = "Shop Admin 회원가입 요청")
public record SignUpRequest(@Schema(description = "Email", requiredMode = Schema.RequiredMode.REQUIRED)
							String email,
							@Schema(description = "Password", requiredMode = Schema.RequiredMode.REQUIRED)
							String password,
							@Schema(description = "Name", requiredMode = Schema.RequiredMode.REQUIRED)
							String name,
							@Schema(description = "Logo Image Url", requiredMode = Schema.RequiredMode.REQUIRED)
							String logoImageUrl,
							@Schema(description = "Mall Number", requiredMode = Schema.RequiredMode.REQUIRED)
							String mall_number,
							@Schema(description = "Phone Number", requiredMode = Schema.RequiredMode.REQUIRED)
							String phone_number,
							@Schema(description = "Review Title Active", requiredMode = Schema.RequiredMode.REQUIRED)
							Boolean title,
							@Schema(description = "Review Author Active", requiredMode = Schema.RequiredMode.REQUIRED)
							Boolean author,
							@Schema(description = "Review Point Active", requiredMode = Schema.RequiredMode.REQUIRED)
							Boolean point,
							@Schema(description = "Review Media Active", requiredMode = Schema.RequiredMode.REQUIRED)
							Boolean media,
							@Schema(description = "Review Content Active", requiredMode = Schema.RequiredMode.REQUIRED)
							Boolean content,
							@Schema(description = "Review CreatedAt Active", requiredMode = Schema.RequiredMode.REQUIRED)
							Boolean createdAt,
							@Schema(description = "Review UpdatedAt Active", requiredMode = Schema.RequiredMode.REQUIRED)
							Boolean updatedAt,
							@Schema(description = "Design Theme Name", requiredMode = Schema.RequiredMode.REQUIRED)
							String theme_name) {

	public SignUpRequest {
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);
		Objects.requireNonNull(name);
		Objects.requireNonNull(logoImageUrl);
		Objects.requireNonNull(mall_number);
		Objects.requireNonNull(phone_number);
		Objects.requireNonNull(title);
		Objects.requireNonNull(author);
		Objects.requireNonNull(point);
		Objects.requireNonNull(media);
		Objects.requireNonNull(content);
		Objects.requireNonNull(createdAt);
		Objects.requireNonNull(updatedAt);
		Objects.requireNonNull(theme_name);
	}
}
