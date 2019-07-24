package com.example.captcha.domain;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaptchaValidation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4903285003676023782L;

	private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaValidation.class);

	private String captchaSecret;
	private String captchaToken;
	private String text;

	private transient TextEncoder encoder;

	public CaptchaValidation() {
		encoder = new TextEncoder();
	}

	public CaptchaValidation(String text, String captchaSecret) {
		this();
		this.text = text;
		this.captchaSecret = captchaSecret;
	}

	public String getCaptchaSecret() {
		return captchaSecret;
	}

	public void setCaptchaSecret(String captchaSecret) {
		this.captchaSecret = captchaSecret;
	}

	public String getCaptchaToken() {
		return captchaToken;
	}

	public void setCaptchaToken(String captchaToken) {
		this.captchaToken = captchaToken;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isValid() {
		boolean hasNulls = captchaSecret == null || text == null || captchaSecret.isEmpty() || text.isEmpty();
		LOGGER.debug("Has nulls: {}", hasNulls);
		String digest = encoder.generateSecret(this.text);
		LOGGER.debug("Digest: {}", digest);
		boolean valid = !hasNulls && digest.equals(captchaSecret);
		LOGGER.debug("Valid: {}", valid);
		return valid;
	}

	@Override
	public String toString() {
		return "CaptchaValidation [captchaSecret=" + captchaSecret + ", captchaToken=" + captchaToken + ", text=" + text
				+ "]";
	}

}
