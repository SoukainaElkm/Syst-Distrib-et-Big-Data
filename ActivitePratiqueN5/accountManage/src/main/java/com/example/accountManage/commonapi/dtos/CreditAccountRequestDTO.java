package com.example.accountManage.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CreditAccountRequestDTO {
    public String accountId;
    public double amountCredited;
    public String currency;
}
