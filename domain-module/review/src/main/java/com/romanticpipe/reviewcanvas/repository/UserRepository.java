package com.romanticpipe.reviewcanvas.repository;

import com.romanticpipe.reviewcanvas.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByMemberIdAndMallId(String memberId, String mallId);
}
