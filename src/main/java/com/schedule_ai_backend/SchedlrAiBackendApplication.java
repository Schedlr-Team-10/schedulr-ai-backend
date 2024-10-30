package com.schedule_ai_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SchedlrAiBackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(SchedlrAiBackendApplication.class, args);
	}
}
