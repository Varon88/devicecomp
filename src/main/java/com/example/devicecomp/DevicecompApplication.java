package com.example.devicecomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DevicecompApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevicecompApplication.class, args);
	}

}
