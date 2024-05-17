package com.romanticpipe.reviewcanvas.service;

import org.springframework.stereotype.Service;

import com.romanticpipe.reviewcanvas.domain.User;
import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.UserErrorCode;
import com.romanticpipe.reviewcanvas.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User validByUserId(String memberId) {
		return userRepository.findByMemberId(memberId)
			.orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));
	}
}
