package com.example.accountManage.commonapi.commands;

import lombok.Getter;
import com.example.accountManage.commonapi.enums.AccountStatus;

public class CreateAccountCommand extends BaseCommand<String> {
    @Getter public double initialBalance;
    @Getter public String currency;
    @Getter public AccountStatus status;

    public CreateAccountCommand(String id, double initialBalance, String currency,AccountStatus status) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.status = status;
    }
}
