package com.example.captcha.domain;

public class CaptchaGenerationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9026608831168324727L;

	public CaptchaGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CaptchaGenerationException(String message) {
		super(message);
	}

	public CaptchaGenerationException(Throwable cause) {
		super(cause);
	}

}
