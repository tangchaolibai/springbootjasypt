package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.resolver.EncryptionPropertyResolver;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyResolver;

@Configuration
public class EncryptablePropertyConfig {

	@Bean
	public EncryptablePropertyResolver encryptablePropertyResolver() {
		return new EncryptionPropertyResolver();
	}
	
}
