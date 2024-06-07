package com.romanticpipe.reviewcanvas.domain.review.application.usecase;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewByShopAdminRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.CreateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.request.UpdateReviewRequest;
import com.romanticpipe.reviewcanvas.domain.review.application.usecase.response.GetReviewDetailResponse;
import com.romanticpipe.reviewcanvas.dto.PageResponse;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.ReplyFilter;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForShopAdmin;
import com.romanticpipe.reviewcanvas.enumeration.ReviewFilterForUser;
import com.romanticpipe.reviewcanvas.enumeration.ReviewPeriod;
import com.romanticpipe.reviewcanvas.enumeration.Score;
import org.springframework.web.multipart.MultipartFile;

import java.util.EnumSet;
import java.util.List;

public interface ReviewUseCase {
	PageResponse<GetReviewDetailResponse> getReviewsForUser(
		String mallId, Long productNo, String memberId, PageableRequest pageableRequest, ReviewFilterForUser filter);

	PageResponse<GetReviewDetailResponse> getReviewsForDashboard(
		Integer integer, Long productId, PageableRequest pageable, ReviewPeriod reviewPeriod,
		EnumSet<ReviewFilterForShopAdmin> reviewFilters, EnumSet<Score> score, EnumSet<ReplyFilter> replyFilters);

	void createReview(String mallId, Long productNo, CreateReviewRequest createReviewRequest,
					  List<MultipartFile> reviewFiles);

	void updateReview(String mallId, String memberId, Long reviewId,
					  UpdateReviewRequest updateReviewRequest, List<MultipartFile> reviewFiles);

	GetReviewDetailResponse getReviewForUser(Long reviewId, String mallId, String memberId);

	void deleteReviewByPublicView(String mallId, String memberId, Long reviewId);

	void createReviewByShopAdmin(Integer shopAdminId, Long productId,
								 CreateReviewByShopAdminRequest createReviewByShopAdminRequest,
								 List<MultipartFile> reviewFiles);

	void deleteReviewByShopAdmin(Integer shopAdminId, Long reviewId);

	PageResponse<GetReviewDetailResponse> getReviewsInMyPage(String mallId, String memberId,
															 PageableRequest of, ReviewFilterForUser filter);

	void updateReviewByShopAdmin(Integer shopAdminId, Long reviewId,
								 UpdateReviewRequest updateReviewRequest, List<MultipartFile> reviewFiles);

	PageResponse<GetReviewDetailResponse> getProductReviewsInMyPage(String mallId, String memberId, Long productNo,
																	PageableRequest of, ReviewFilterForUser filter);
}
