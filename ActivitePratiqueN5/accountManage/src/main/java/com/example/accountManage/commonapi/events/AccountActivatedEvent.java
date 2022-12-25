package com.example.accountManage.commonapi.events;

import lombok.Getter;
import com.example.accountManage.commonapi.enums.AccountStatus;

public class AccountActivatedEvent extends BaseEvent<String> {
    @Getter private AccountStatus accountStatus;
    public AccountActivatedEvent(String id, AccountStatus accountStatus) {
        super(id);
        this.accountStatus = accountStatus;
    }
}
