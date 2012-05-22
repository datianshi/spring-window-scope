package com.sample.scopes.config;

import com.sample.scopes.domain.ValueBean;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.request.WindowScope;

import java.util.HashMap;
import java.util.Map;

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

	@Bean
	public CustomScopeConfigurer customScopeConfigurer() {
		Map<String, Object> scopes = new HashMap<String, Object>(1);
		scopes.put("window", new WindowScope());

		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(scopes);
		return configurer;
	}
}
