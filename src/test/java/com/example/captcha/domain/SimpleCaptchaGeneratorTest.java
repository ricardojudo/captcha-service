package com.example.captcha.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleCaptchaGeneratorTest {

	private SimpleCaptchaGenerator captchaGenerator;

	@BeforeEach
	public void setUp() {
		captchaGenerator = new SimpleCaptchaGenerator();
	}

	@Test
	public void generateCaptchaDefinitionTest() throws Exception {
		assertThat(captchaGenerator.generate(), instanceOf(Captcha.class));
		assertThat(captchaGenerator.generate(), notNullValue());		
	}
	
	@Test
	public void generateCaptchaValidTest() throws Exception{
		Captcha captcha = captchaGenerator.generate();
		assertThat("captchaSecret null",captcha.getCaptchaSecret(), notNullValue());
		assertThat("captchaToken",captcha.getCaptchaToken(), notNullValue());
		assertThat("image", captcha.getImage(), notNullValue());
	}
	

}
