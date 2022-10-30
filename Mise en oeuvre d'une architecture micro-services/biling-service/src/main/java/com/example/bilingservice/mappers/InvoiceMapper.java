package com.example.bilingservice.mappers;

import com.example.bilingservice.DTO.InvoiceRequestDTO;
import com.example.bilingservice.DTO.InvoiceResponseDTO;
import com.example.bilingservice.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}
