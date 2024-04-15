package com.romanticpipe.reviewcanvas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewUseCase;
import com.romanticpipe.reviewcanvas.dto.PageableRequest;
import com.romanticpipe.reviewcanvas.enumeration.Direction;
import com.romanticpipe.reviewcanvas.exception.BusinessException;

@SpringBootTest
class ReviewCanvasBackApplicationTests {

	@Autowired
	private ReviewUseCase reviewUseCase;

	@Test
	void contextLoads() {
	}

	@DisplayName("특정 shop admin의 MyReviewDesign 리스트 조회 테스트")
	@Test
	void getMyReviewDesignsTest() {
		PageableRequest pageableRequest = PageableRequest.of(0, 10, Direction.DESC);
		long validShopAdminId0 = 1L;
		long validShopAdminId1 = 2L;
		long validShopAdminId2 = 3L;
		long invalidShopAdminId0 = 0L;
		long invalidShopAdminId1 = -1L;

		assertDoesNotThrow(() -> reviewUseCase.getMyReviewDesigns(validShopAdminId0, pageableRequest));
		assertDoesNotThrow(() -> reviewUseCase.getMyReviewDesigns(validShopAdminId1, pageableRequest));
		assertDoesNotThrow(() -> reviewUseCase.getMyReviewDesigns(validShopAdminId2, pageableRequest));
		assertThrows(BusinessException.class,
			() -> reviewUseCase.getMyReviewDesigns(invalidShopAdminId0, pageableRequest));
		assertThrows(BusinessException.class,
			() -> reviewUseCase.getMyReviewDesigns(invalidShopAdminId1, pageableRequest));
	}

}
