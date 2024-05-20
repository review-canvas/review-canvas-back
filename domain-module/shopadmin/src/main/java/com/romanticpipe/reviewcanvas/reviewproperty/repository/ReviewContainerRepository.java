package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewContainer;

public interface ReviewContainerRepository extends JpaRepository<ReviewContainer, Integer> {
	Optional<ReviewContainer> findByShopAdminId(Integer shopAdminId);
}
