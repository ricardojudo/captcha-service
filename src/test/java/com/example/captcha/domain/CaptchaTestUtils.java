package com.example.captcha.domain;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CaptchaTestUtils {

	public static void runMultiple(final int threads, final int loop, final RunMultiple runnable)
			throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int j = 0; j < threads; j++) {
			es.execute(()->{
					for (int i = 0; i < loop; i++) {
						System.out.println(Thread.currentThread().getName() + " : " + i);
						try {
							runnable.run();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			);
		}
		es.shutdown();
		es.awaitTermination(1, TimeUnit.HOURS);
	}

	@FunctionalInterface
	public static interface RunMultiple {
		void run() throws Exception;
	}
	
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
