package com.waldron.online_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class OnlineLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);
	}

}
