package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romanticpipe.reviewcanvas.domain.User;

public interface UsersRepository extends JpaRepository<User, Long> {
	Optional<User> findByMemberIdAndMallId(String memberId, String mallId);
}
