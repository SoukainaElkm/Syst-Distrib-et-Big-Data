package com.example.immatriculationcommand;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImmatriculationCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImmatriculationCommandApplication.class, args);
	}
	@Bean
	public CommandBus commandBus(){
		return SimpleCommandBus.builder().build();
	}

}
