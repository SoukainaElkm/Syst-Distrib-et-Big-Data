package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder().name("soukaina1").email("soukaina1@gmail.com").build(),
					Customer.builder().name("soukaina2").email("soukaina2@gmail.com").build(),
					Customer.builder().name("soukaina3").email("soukaina3@gmail.com").build()
			));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}