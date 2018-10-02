package com.infoasdp;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.infoasdp.auth.AuthenticationEngine;
import com.infoasdp.auth.AuthenticationEngineImpl;

import liquibase.integration.spring.SpringLiquibase;

/**
 * Author Septa
 *
 */
@SpringBootApplication
public class Application 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(Application.class, args);
    }
    
    @Bean
	public SpringLiquibase liquibase(DataSource datasource) {
		SpringLiquibase config = new SpringLiquibase();
		config.setDataSource(datasource);
		config.setChangeLog("classpath:/liquibase/db.changelog-master.xml");
		return config;
	}
    
    @Bean
	public AuthenticationEngine authenticationEngineDBImpl() {
		return new AuthenticationEngineImpl();
	}
}
