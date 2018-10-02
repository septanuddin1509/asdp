package com.infoasdp.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.infoasdp.Application;
import com.infoasdp.DaoSpringConfig;


@Configuration
@EnableAutoConfiguration
@Import({ DaoSpringConfig.class, Application.class})
@EnableTransactionManagement
public class ApplicationMock {

}
