package com.hacademy.ncs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.OAS_30)
								.select()
									.apis(RequestHandlerSelectors.basePackage("com.hacademy.ncs.controller"))
									.paths(PathSelectors.any())
								.build()
									.apiInfo(new ApiInfoBuilder()
															.title("NCS module searcher")
															.description("NCS 학습모듈 검색 도우미")
															.version("0.0.1")
															.build());
	}
}
