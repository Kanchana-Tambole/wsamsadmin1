package com.ws.spring.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationInitializer implements ApplicationListener<ApplicationReadyEvent> {
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
	}
}
