package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewContainerRepository extends JpaRepository<ReviewContainer, Integer> {
	Optional<ReviewContainer> findByShopAdminId(Integer shopAdminId);
}
