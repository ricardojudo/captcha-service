package com.example.captcha.domain;

import static com.google.code.kaptcha.Constants.KAPTCHA_BACKGROUND_CLR_FROM;
import static com.google.code.kaptcha.Constants.KAPTCHA_BACKGROUND_CLR_TO;
import static com.google.code.kaptcha.Constants.KAPTCHA_NOISE_COLOR;
import static com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH;
import static com.example.captcha.domain.CaptchaUtils.chooseRandom;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

@Service
public class SimpleCaptchaGenerator implements CaptchaGenerator {

	private final String[] noiseColors = { "red", "black", "darkGray" };
	private final String[] backgroundColorsFrom = { "black", "blue" };
	private final String[] backgroundColorsTo = { "yellow", "white", "green" };
	private TextEncoder encoder;

	public Captcha generate() throws CaptchaGenerationException {
		Captcha captcha = new Captcha();
		encoder = new TextEncoder();
		try {
			buildCaptcha(captcha);
		} catch (Exception e) {
			String message = String.format("Occurio un error al generar el CAPTCHA: %s", e.getLocalizedMessage());
			throw new CaptchaGenerationException(message, e);
		}
		return captcha;
	}

	private void buildCaptcha(Captcha captcha) throws Exception {
		ImageIO.setUseCache(false);
		Properties props = new Properties();
		props.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "6");
		props.setProperty(KAPTCHA_NOISE_COLOR, chooseRandom(noiseColors));
		props.setProperty(KAPTCHA_BACKGROUND_CLR_TO, chooseRandom(backgroundColorsTo));
		props.setProperty(KAPTCHA_BACKGROUND_CLR_FROM, chooseRandom(backgroundColorsFrom));

		Config config = new Config(props);
		Producer kaptchaProducer = config.getProducerImpl();

		String capText = kaptchaProducer.createText();
		BufferedImage bi = kaptchaProducer.createImage(capText);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bi, "jpg", out);
		String imageb64 = encoder.toBase64(out.toByteArray());
		out.close();

		// captcha.setCaptchaToken(capText);
		captcha.setText(capText);
		captcha.setCaptchaSecret(encoder.generateSecret(capText));
		captcha.setImage(imageb64);

	}

}
