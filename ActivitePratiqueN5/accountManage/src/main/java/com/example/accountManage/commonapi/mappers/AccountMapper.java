package com.example.accountManage.commonapi.mappers;

import com.example.accountManage.commonapi.dtos.AccountResponseDTO;
import com.example.accountManage.query.entities.Account;

//@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponseDTO toAccountResponseDTO(Account account);
}
