package com.example.captcha.domain;

public interface CaptchaGenerator {
	Captcha generate() throws CaptchaException;
}
