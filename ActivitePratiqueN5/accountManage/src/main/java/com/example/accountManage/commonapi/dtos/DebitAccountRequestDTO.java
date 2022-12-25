package com.example.accountManage.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DebitAccountRequestDTO {
    public String accountId;
    public double amountDebited;
    public String currency;
}
