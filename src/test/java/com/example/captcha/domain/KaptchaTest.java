package com.example.captcha.domain;

import static com.example.captcha.domain.CaptchaTestUtils.runMultiple;
import static com.example.captcha.domain.CaptchaUtils.chooseRandom;
import static com.google.code.kaptcha.Constants.KAPTCHA_BACKGROUND_CLR_FROM;
import static com.google.code.kaptcha.Constants.KAPTCHA_BACKGROUND_CLR_TO;
import static com.google.code.kaptcha.Constants.KAPTCHA_NOISE_COLOR;
import static com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

public class KaptchaTest {

	private Properties props = new Properties();
	private Random random;

	String[] noiseColors = { "red", "black", "darkGray" };
	String[] backgroundColorsFrom = { "black", "blue" };
	String[] backgroundColorsTo = { "yellow", "white", "green" };
	String[] fontColors = {};

	@BeforeEach
	public void setUp() {
		random = new Random();
		ImageIO.setUseCache(false);
	}

	@Test
	public void test() throws Exception {

		props.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
		props.setProperty(KAPTCHA_NOISE_COLOR, chooseRandom(noiseColors));
		props.setProperty(KAPTCHA_BACKGROUND_CLR_TO, chooseRandom(backgroundColorsTo));
		props.setProperty(KAPTCHA_BACKGROUND_CLR_FROM, chooseRandom(backgroundColorsFrom));
		Config config = new Config(this.props);

		Producer kaptchaProducer = config.getProducerImpl();
		String capText = kaptchaProducer.createText();
		System.out.println(capText);
		BufferedImage bi = kaptchaProducer.createImage(capText);

		String fileName = String.format("captcha-%s.jpg", random.nextInt());

		FileOutputStream out = new FileOutputStream(new File("/tmp", fileName));
		ImageIO.write(bi, "jpg", out);
		out.close();

	}

	@Test
	public void testInMemory() throws Exception {

		props.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
		props.setProperty(KAPTCHA_NOISE_COLOR, chooseRandom(noiseColors));
		props.setProperty(KAPTCHA_BACKGROUND_CLR_TO, chooseRandom(backgroundColorsTo));
		props.setProperty(KAPTCHA_BACKGROUND_CLR_FROM, chooseRandom(backgroundColorsFrom));

		Config config = new Config(this.props);
		Producer kaptchaProducer = config.getProducerImpl();

		String capText = kaptchaProducer.createText();
		System.out.println(capText);
		BufferedImage bi = kaptchaProducer.createImage(capText);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bi, "jpg", out);

		Encoder encoder = Base64.getEncoder();
		String imageb64 = encoder.encodeToString(out.toByteArray());
		imageb64 = null;
	}

	// @Test
	public void multipleCaptcha() throws Exception {
		/*
		 * 60.140s 10,000 captchas ~90% CPU 102.329s 30,000 3 threads ~99% CPU
		 * 
		 * 
		 * Con configuracion 122.875s 30,000 3 threads ~99% CPU
		 */
		runMultiple(3, 10000, ()->{test();});
	}

	// @Test
	public void multipleCaptchaInMemory() throws Exception {
		/*
		 * 10,000 captchas ~90% CPU 81.101s 30,000 3 threads (10,000 p/thread)
		 * ~99% CPU 178.227s 10,000 1000 threads (100 p/thread) ~99% CPU
		 * 
		 * Con configuracion 45.704 10,000 captchas 1 thread 62.306s 30,000 3
		 * threads (10,000 p/thread) ~99% CPU
		 */

		runMultiple(3, 10000, ()->{testInMemory();});
	}

}
