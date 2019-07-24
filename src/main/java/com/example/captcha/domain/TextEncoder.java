package com.example.captcha.domain;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextEncoder {

	private static final Logger LOGGER = LoggerFactory.getLogger(TextEncoder.class);

	public String toBase64(String text) {
		return toBase64(text.getBytes());
	}

	public String toBase64(byte[] bytes) {
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(bytes);
	}

	public String generateSecret(String text) {
		byte[] digest = {};
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			StringBuilder sb = new StringBuilder(CaptchaConstants.SALT);
			sb.append(text);
			digest = messageDigest.digest(sb.toString().getBytes());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return toBase64(digest);
	}
}