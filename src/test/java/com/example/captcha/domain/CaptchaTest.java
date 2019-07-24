package com.example.captcha.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasProperty.hasProperty;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import java.io.Serializable;

import org.junit.jupiter.api.Test;

public class CaptchaTest {

	
	


	@Test
	public void propertiesTest() {
		Captcha captcha = new Captcha();
		assertThat(captcha, hasProperty("image"));
		assertThat(captcha, hasProperty("captchaSecret"));
		assertThat(captcha, hasProperty("captchaToken"));
	}
	
	@Test
	public void definitionTest(){
		Captcha captcha= new Captcha();
		assertThat(captcha, instanceOf(Serializable.class));
	}

}
