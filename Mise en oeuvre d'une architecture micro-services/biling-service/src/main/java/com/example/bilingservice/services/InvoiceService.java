package com.example.bilingservice.services;

import com.example.bilingservice.DTO.InvoiceRequestDTO;
import com.example.bilingservice.DTO.InvoiceResponseDTO;
import com.example.bilingservice.Exceptions.CustomerNotFoundException;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) throws CustomerNotFoundException;
    InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> invoiceByCustomerId(String customerId);
    List<InvoiceResponseDTO> allInvoices();
}