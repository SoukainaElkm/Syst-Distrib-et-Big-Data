package org.enset.customer_service.mappers;

import org.enset.customer_service.DTO.CustomerRequestDTO;
import org.enset.customer_service.DTO.CustomerResponseDTO;
import org.enset.customer_service.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);
}
