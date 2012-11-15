package com.sample.scopes.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WindowScope;
import org.springframework.web.context.request.WindowScopeInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CustomScopeConfiguration extends WebMvcConfigurationSupport {
	@Bean
	public CustomScopeConfigurer customScopeConfigurer() {
		Map<String, Object> scopes = new HashMap<String, Object>(1);
		scopes.put("window", new WindowScope());

		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(scopes);
		return configurer;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(windowScopeInterceptor());
	}

	@Bean
	public HandlerInterceptor windowScopeInterceptor() {
		return new WindowScopeInterceptor();
	}
}
