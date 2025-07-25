package com.ws.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Configuration
@EnableScheduling
public class WsamsadminApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsamsadminApplication.class, args);
	}

}
