package com.example.captcha.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.xml.bind.annotation.XmlTransient;

public class Captcha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7577470198782198180L;

	private String captchaSecret;
	private String captchaToken;
	private String image;
	private String text;

	public Captcha() {
		captchaToken = UUID.randomUUID().toString();
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@XmlTransient
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
