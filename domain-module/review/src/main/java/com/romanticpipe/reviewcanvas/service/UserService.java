package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.ReviewErrorCode;
import com.romanticpipe.reviewcanvas.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User validByMemberIdAndMallId(String memberId, String mallId) {
		return userRepository.findByMemberIdAndMallId(memberId, mallId)
			.orElseThrow(() -> new BusinessException(ReviewErrorCode.USER_NOT_FOUND));
	}
}
