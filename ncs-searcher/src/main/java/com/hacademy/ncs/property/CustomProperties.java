package com.hacademy.ncs.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "ncs.api")
public class CustomProperties {
	private String serviceKey; 
}
