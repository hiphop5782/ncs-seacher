package com.hacademy.ncs.configuration;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JsonConfiguration {
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public File getBaseDirectory(ServletContext servletContext) {
		File directory = new File(servletContext.getRealPath("/json"));
		directory.mkdirs();
		return directory;
	}
	
	public static final String MODULE_JSON = "modules.json";
	
}
