package com.romanticpipe.reviewcanvas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.romanticpipe.reviewcanvas.domain.review.application.usecase.ReviewUseCase;
import com.romanticpipe.reviewcanvas.exception.BusinessException;

@SpringBootTest
class ReviewCanvasBackApplicationTests {
	@Autowired
	private ReviewUseCase reviewUseCase;

	@Test
	void contextLoads() {
	}

	@DisplayName("선택 중인 리뷰 디자인 조회 테스트")
	@Test
	public void getSelectedReviewDesignTest() {
		long validShopAdminId0 = 1;
		long validShopAdminId1 = 2;
		long validShopAdminId2 = 3;
		long invalidShopAdminId0 = 0;
		long invalidShopAdminId1 = -1;

		assertDoesNotThrow(() -> {
			reviewUseCase.getSelectedReviewDesign(validShopAdminId0);
		});
		assertDoesNotThrow(() -> {
			reviewUseCase.getSelectedReviewDesign(validShopAdminId1);
		});
		assertDoesNotThrow(() -> {
			reviewUseCase.getSelectedReviewDesign(validShopAdminId2);
		});
		assertThrows(BusinessException.class, () -> {
			reviewUseCase.getSelectedReviewDesign(invalidShopAdminId0);
		});
		assertThrows(BusinessException.class, () -> {
			reviewUseCase.getSelectedReviewDesign(invalidShopAdminId1);
		});
	}

}
