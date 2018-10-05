package com.infoasdp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;

import com.infoasdp.service.MenuDaoService;
import com.infoasdp.service.MenuDaoServiceImpl;
import com.infoasdp.service.UserDaoService;
import com.infoasdp.service.UserDaoServiceImpl;

@Configuration
public class DaoSpringConfig {

	@Bean
	public UserDaoService userDaoService() {
		return new UserDaoServiceImpl();
	}
	
	@Bean
	public MenuDaoService menuDaoService() {
		return new MenuDaoServiceImpl();
	}
	/*
	@Bean
	public CommonResponseGenerator commonResponseGenerator() {
		return new CommonResponseGenerator();
	}
	
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public RequestContext requestContext() {
		return new RequestContext();
	}
	
	@Bean
	public ResponseMapping responseMapping() {
		return new ResponseMapping();
	}*/
}
