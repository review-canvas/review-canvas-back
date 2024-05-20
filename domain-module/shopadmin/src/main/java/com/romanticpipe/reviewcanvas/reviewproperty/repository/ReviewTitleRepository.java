package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewTitle;
import com.romanticpipe.reviewcanvas.reviewproperty.domain.value.ReviewTitleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewTitleRepository extends JpaRepository<ReviewTitle, Integer> {
	Optional<ReviewTitle> findByShopAdminIdAndReviewTitleType(Integer shopAdminId, ReviewTitleType reviewTitleType);

}
