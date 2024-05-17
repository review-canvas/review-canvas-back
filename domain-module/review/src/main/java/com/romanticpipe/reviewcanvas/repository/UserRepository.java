package com.romanticpipe.reviewcanvas.repository;

import java.util.Optional;

import com.romanticpipe.reviewcanvas.domain.User;

public interface UserRepository {
	Optional<User> findByMemberId(String memberId);
}
