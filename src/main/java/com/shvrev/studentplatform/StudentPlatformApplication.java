package com.shvrev.studentplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJms
@EnableAsync
public class StudentPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentPlatformApplication.class, args);
	}
}
