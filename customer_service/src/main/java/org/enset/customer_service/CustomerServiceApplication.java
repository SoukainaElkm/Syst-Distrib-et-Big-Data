package org.enset.customer_service;

import org.enset.customer_service.DTO.CustomerRequestDTO;
import org.enset.customer_service.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerService customerService){
        return args -> {
            customerService.save(new CustomerRequestDTO("A10", "Soukaina","soukaina@gmail.com"));
            customerService.save(new CustomerRequestDTO("A20", "Salma","salma@gmail.com"));
        };
    }
}
