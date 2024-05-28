package com.romanticpipe.reviewcanvas.domain.review.presentation.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewLikeUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewLikeController implements ReviewLikeApi {

	private final ReviewLikeUseCase reviewLikeUseCase;

}
