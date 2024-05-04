package com.romanticpipe.reviewcanvas.reviewproperty.repository;

import com.romanticpipe.reviewcanvas.reviewproperty.domain.ReviewLayout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLayoutRepository extends JpaRepository<ReviewLayout, Integer> {

	Optional<ReviewLayout> findByShopAdminId(Integer shopAdminId);
}
