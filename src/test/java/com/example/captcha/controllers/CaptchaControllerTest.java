package com.example.captcha.controllers;

import static com.example.captcha.domain.CaptchaTestUtils.convertObjectToJsonBytes;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.captcha.domain.Captcha;
import com.example.captcha.domain.CaptchaException;
import com.example.captcha.domain.CaptchaGenerator;
import com.example.captcha.domain.CaptchaValidation;

@SpringJUnitWebConfig
@SpringBootTest
class CaptchaControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private CaptchaController controller;
	
	@Autowired
	private CaptchaGenerator captchaGenerator;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	//@Test
	void testGet() throws Exception {
		mockMvc.perform(
				get("/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.lineaCaptura", not(nullValue())));
	}

	//@Test
	void testValidate() throws Exception {
		Captcha captcha = captchaGenerator.generate();
		CaptchaValidation captchaValidation = new CaptchaValidation(captcha.getText(), captcha.getCaptchaSecret());
		mockMvc.perform(
				post("/").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(captchaValidation)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.lineaCaptura", not(nullValue())));
	}

}
