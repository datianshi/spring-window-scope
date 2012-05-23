package com.sample.scopes.config;

import com.sample.scopes.domain.ValueBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class WebAppConfiguration {
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public ValueBean sessionScopedBean() {
		return new ValueBean("session scope");
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public ValueBean requestScopedBean() {
		return new ValueBean("request scope");
	}

	@Bean
	@Scope(value = "window", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public ValueBean windowScopedBean() {
		return new ValueBean("window scope");
	}
}
