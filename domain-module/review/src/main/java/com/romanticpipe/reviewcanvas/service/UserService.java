package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public Optional<User> findUser(String memberId, String mallId) {
		return userRepository.findByMemberIdAndMallId(memberId, mallId);
	}

	public User save(User user) {
		return userRepository.save(user);
	}
}
