package com.example.accountManage.query.repositories;

import com.example.accountManage.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
