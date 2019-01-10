package com.dataprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.dataprocess.*"})
public class DataProcessApplication {

	public static void main(String[] args) {

		 SpringApplication.run(DataProcessApplication.class, args);

	}

}
