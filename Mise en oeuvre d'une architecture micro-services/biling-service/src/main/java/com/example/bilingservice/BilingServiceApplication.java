package com.example.bilingservice;

import com.example.bilingservice.DTO.InvoiceRequestDTO;
import com.example.bilingservice.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BilingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(9000), "A10"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(5000), "A10"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(35000), "A20"));
        };
    }
}
