package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewDetailResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import org.springframework.web.multipart.MultipartFile;

import java.util.EnumSet;
import java.util.List;

public interface ReviewUseCase {
	PageResponse<GetReviewDetailResponse> getReviewsForUser(
		String mallId, Long productNo, PageableRequest pageableRequest, ReviewFilterForUser filter);

	PageResponse<GetReviewDetailResponse> getReviewsForDashboard(
		Long productId, PageableRequest pageable, EnumSet<ReviewFilterForShopAdmin> reviewFilters,
		EnumSet<Score> score, EnumSet<ReplyFilter> replyFilters);

	void createReview(String mallId, Long productNo, CreateReviewRequest createReviewRequest,
					  List<MultipartFile> reviewImages);

	void updateReview(String mallId, String memberId, Long reviewId,
					  UpdateReviewRequest updateReviewRequest, List<MultipartFile> reviewImages);

	GetReviewDetailResponse getReviewForUser(Long reviewId);
}
