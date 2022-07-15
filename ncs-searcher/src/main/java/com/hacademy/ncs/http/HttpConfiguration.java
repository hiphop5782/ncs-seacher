package com.hacademy.ncs.http;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;

@Configuration
public class HttpConfiguration {
	
	@Bean
	public OkHttpClient httpClient() {
		return new OkHttpClient().newBuilder()
							.connectTimeout(10, TimeUnit.SECONDS)
							.readTimeout(30, TimeUnit.SECONDS)
							.build();
	}
	
}
