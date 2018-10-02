package com.infoasdp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
