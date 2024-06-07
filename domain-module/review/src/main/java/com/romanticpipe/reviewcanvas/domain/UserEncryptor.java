package com.romanticpipe.reviewcanvas.domain;

import com.romanticpipe.reviewcanvas.util.AesUtils;

import javax.crypto.SecretKey;

public class UserEncryptor {

	private final SecretKey secretKey;

	public UserEncryptor() {
		String userSecretKey = System.getenv("USER_ENCRYPTOR_SECRET_KEY");
		this.secretKey = AesUtils.generateKey(userSecretKey);
	}

	public String encrypt(String userData) {
		return AesUtils.encrypt(userData, this.secretKey);
	}

	public String decrypt(String encryptedUserData) {
		return AesUtils.decrypt(encryptedUserData, this.secretKey);
	}
}
