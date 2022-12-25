package com.example.accountManage.query.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.accountManage.commonapi.enums.OperationType;
import com.example.accountManage.commonapi.events.AccountActivatedEvent;
import com.example.accountManage.commonapi.events.AccountCreatedEvent;
import com.example.accountManage.commonapi.events.AccountCreditedEvent;
import com.example.accountManage.commonapi.events.AccountDebitedEvent;
import com.example.accountManage.commonapi.queries.FindAccountQuery;
import com.example.accountManage.commonapi.queries.FindAllAccountsQuery;
import com.example.accountManage.query.entities.Account;
import com.example.accountManage.query.entities.Operation;
import com.example.accountManage.query.repositories.AccountRepository;
import com.example.accountManage.query.repositories.OperationRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    //private AccountMapper accountMapper;

    // on rejoue les événements émmit par les commandes
    @EventHandler
    @Transactional
    public void on(AccountCreatedEvent event) {
        log.info("AccountCreatedEvent: {}", event);
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getInitialBalance());
        account.setCurrency(event.getCurrency());
        accountRepository.save(account);
        Operation operation = new Operation();
        operation.setAmount(event.getInitialBalance());
        operation.setCurrency(event.getCurrency());
        operation.setOperationDate(event.dateCreation);
        operation.setType(OperationType.CREATION);
    }

    @EventHandler
    @Transactional
    public void on(AccountDebitedEvent event) {
        log.info("AccountDebitedEvent: {}", event);
        Account account = accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() - event.getAmountDebited());
        accountRepository.save(account);
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setType(OperationType.DEBIT);
        operation.setCurrency(event.getCurrency());
        operation.setAmount(event.getAmountDebited());
        operation.setOperationDate(event.getDateTransaction());
        operationRepository.save(operation);
    }

    @EventHandler
    @Transactional
    public void on(AccountCreditedEvent event) {
        log.info("AccountCreditedEvent: {}", event);
        Account account = accountRepository.findById(event.getId()).get();
        account.setBalance(account.getBalance() + event.getAmountCredited());
        accountRepository.save(account);
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setCurrency(event.getCurrency());
        operation.setType(OperationType.CREDIT);
        operation.setAmount(event.getAmountCredited());
        operation.setOperationDate(event.getDateTransaction());
        operationRepository.save(operation);
    }

    @EventHandler
    @Transactional
    public void on(AccountActivatedEvent event) {
        log.info("AccountActivatedEvent: {}", event);
        Account account = accountRepository.findById(event.getId()).get();
        account.setStatus(event.getAccountStatus());
        accountRepository.save(account);
    }
/*
    @QueryHandler // AccountResponseDTO to use in the query as response
    public List<AccountResponseDTO> on(FindAllAccountsQuery query) {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::toAccountResponseDTO).collect(Collectors.toList());
    }
    @QueryHandler
    public AccountResponseDTO on(FindAccountQuery query){
        Account account = accountRepository.findById(query.getId()).get();
        return accountMapper.toAccountResponseDTO(account);
    }

 */
    @QueryHandler // AccountResponseDTO to use in the query as response
    public List<Account> on(FindAllAccountsQuery query) {
        return accountRepository.findAll();
    }
    @QueryHandler
    public Account on(FindAccountQuery query){
        return accountRepository.findById(query.getId()).get();
    }
}