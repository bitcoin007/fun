package com.hero.fun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod(RequestMethod.OPTIONS.name());
		config.addAllowedMethod(RequestMethod.GET.name());
		config.addAllowedMethod(RequestMethod.POST.name());
		config.addAllowedMethod(RequestMethod.PUT.name());
		config.addAllowedMethod(RequestMethod.DELETE.name());
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}