package com.example.captcha.domain;

import java.util.Random;

public class CaptchaUtils {

	public static <T> T chooseRandom(T[] array) {
		Random r = new Random();
		return array[r.nextInt(array.length)];
	}
}
