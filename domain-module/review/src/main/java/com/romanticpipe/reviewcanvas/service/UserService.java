package com.romanticpipe.reviewcanvas.service;

import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.exception.UserNotFoundException;
import com.romanticpipe.reviewcanvas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User validByMemberIdAndMallId(String memberId, String mallId) {
		return userRepository.findByMemberIdAndMallId(memberId, mallId)
			.orElseThrow(() -> new BusinessException(ReviewErrorCode.USER_NOT_FOUND));
	}

	public Optional<User> findUser(String memberId, String mallId) {
		return userRepository.findByMemberIdAndMallId(memberId, mallId);
	}

	public User validateUserByUserId(Long userId) {
		return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
	}

	public User save(User user) {
		return userRepository.save(user);
	}
}
