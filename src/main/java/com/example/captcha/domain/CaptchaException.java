package com.example.captcha.domain;

public class CaptchaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CaptchaException(String message) {
		super(message);
	}

	public CaptchaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CaptchaException(Throwable cause) {
		super(cause);
	}

}
