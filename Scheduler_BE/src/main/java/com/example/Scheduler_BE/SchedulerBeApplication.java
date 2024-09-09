package com.example.Scheduler_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchedulerBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerBeApplication.class, args);
	}

}
