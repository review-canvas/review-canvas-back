package com.romanticpipe.reviewcanvas.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.exception.UserNotFoundException;
import com.romanticpipe.reviewcanvas.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public Optional<User> findUser(String memberId, String mallId) {
		return userRepository.findByMemberIdAndMallId(memberId, mallId);
	}

	public User findUserByUserId(Long userId) {
		return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
	}

	public User save(User user) {
		return userRepository.save(user);
	}
}
