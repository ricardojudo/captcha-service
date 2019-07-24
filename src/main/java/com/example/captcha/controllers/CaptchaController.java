package com.example.captcha.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.captcha.domain.Captcha;
import com.example.captcha.domain.CaptchaException;
import com.example.captcha.domain.CaptchaGenerationException;
import com.example.captcha.domain.CaptchaGenerator;
import com.example.captcha.domain.CaptchaValidation;



@RestController
@RequestMapping("captchas")
public class CaptchaController {

	private Logger logger = LoggerFactory.getLogger(CaptchaController.class);
	
	@Autowired
	private CaptchaGenerator captchaGenerator;
	
	@GetMapping
	public ResponseEntity<Captcha> get() throws CaptchaException {
		Captcha captcha = captchaGenerator.generate();
		return new ResponseEntity<Captcha>(captcha, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> validate(@RequestBody CaptchaValidation validation) {
		logger.debug("Validation: {}", validation.toString());
		ResponseEntity<Void> response = null;
		if (validation.isValid()) {
			response = new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<Void>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return response;
	}
	
	@ExceptionHandler
	public ResponseEntity<CaptchaException> handle(CaptchaException e){
		return new ResponseEntity<CaptchaException>(e, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler
	public ResponseEntity<CaptchaGenerationException> handle(CaptchaGenerationException e){
		return new ResponseEntity<CaptchaGenerationException>(e, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
