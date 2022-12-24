package com.example.radarcommand;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RadarCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadarCommandApplication.class, args);
	}
	@Bean
	public SimpleCommandBus axonServerCommandBus(){
		return SimpleCommandBus.builder().build();
	}

}
