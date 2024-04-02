package com.romanticpipe.reviewcanvas.repository;


import com.romanticpipe.reviewcanvas.domain.Review;
import com.romanticpipe.reviewcanvas.domain.ShopAdmin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopAdminRepository extends JpaRepository<ShopAdmin, Long> {
}
