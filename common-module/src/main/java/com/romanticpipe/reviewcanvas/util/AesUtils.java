package com.romanticpipe.reviewcanvas.util;

import com.romanticpipe.reviewcanvas.exception.BusinessException;
import com.romanticpipe.reviewcanvas.exception.CommonErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AesUtils {

	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/GCM/NoPadding";
	private static final int TAG_LENGTH_BIT = 128;
	private static final int IV_LENGTH_BYTE = 12;

	public static SecretKey generateKey(String key) {
		return new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
	}

	public static byte[] generateIv() {
		byte[] iv = new byte[IV_LENGTH_BYTE];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(iv);
		return iv;
	}

	public static String encrypt(String rawData, SecretKey key) {
		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			byte[] iv = generateIv();
			cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
			byte[] cipherText = cipher.doFinal(rawData.getBytes());
			byte[] cipherTextWithIv = new byte[iv.length + cipherText.length];
			System.arraycopy(iv, 0, cipherTextWithIv, 0, iv.length);
			System.arraycopy(cipherText, 0, cipherTextWithIv, iv.length, cipherText.length);
			return Base64.getEncoder().encodeToString(cipherTextWithIv);
		} catch (Exception e) {
			throw new BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR, e);
		}
	}

	public static String decrypt(String encryptedData, SecretKey key) {
		try {
			byte[] cipherTextWithIv = Base64.getDecoder().decode(encryptedData);
			byte[] iv = new byte[IV_LENGTH_BYTE];
			System.arraycopy(cipherTextWithIv, 0, iv, 0, iv.length);
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
			byte[] plainText = cipher.doFinal(cipherTextWithIv, iv.length, cipherTextWithIv.length - iv.length);
			return new String(plainText);
		} catch (Exception e) {
			throw new BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR, e);
		}
	}
}
