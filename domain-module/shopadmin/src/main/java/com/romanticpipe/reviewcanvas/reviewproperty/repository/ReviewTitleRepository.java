package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitleType;

public interface ReviewTitleRepository extends JpaRepository<ReviewTitle, Integer> {
	Optional<ReviewTitle> findByShopAdminIdAndReviewTitleType(Integer shopAdminId, ReviewTitleType reviewTitleType);

}
