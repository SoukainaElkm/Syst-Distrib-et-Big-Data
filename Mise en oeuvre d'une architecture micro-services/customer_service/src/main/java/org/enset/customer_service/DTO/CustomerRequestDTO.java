package org.enset.customer_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CustomerRequestDTO {
    private String id;
    private String name;
    private String email;
}
