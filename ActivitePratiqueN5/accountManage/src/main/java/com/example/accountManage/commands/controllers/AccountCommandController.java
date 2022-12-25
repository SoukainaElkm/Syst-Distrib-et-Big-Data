package com.example.accountManage.commands.controllers;

import lombok.AllArgsConstructor;
import com.example.accountManage.commonapi.commands.CreateAccountCommand;
import com.example.accountManage.commonapi.commands.CreditAccountCommand;
import com.example.accountManage.commonapi.commands.DebitAccountCommand;
import com.example.accountManage.commonapi.dtos.CreateAccountRequestDTO;
import com.example.accountManage.commonapi.dtos.CreditAccountRequestDTO;
import com.example.accountManage.commonapi.dtos.DebitAccountRequestDTO;
import com.example.accountManage.commonapi.enums.AccountStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/commands/account")
@AllArgsConstructor
@Service
public class AccountCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO createAccountDTO) {
        CompletableFuture<String> response = commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                createAccountDTO.getInitialBalance(),
                createAccountDTO.getCurrency(),
                AccountStatus.CREATED
        ));
        return response;
    }
    @PutMapping(path = "/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO creditAccountRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(
                new CreditAccountCommand(
                        creditAccountRequestDTO.getAccountId(),
                        creditAccountRequestDTO.getAmountCredited(),
                        creditAccountRequestDTO.getCurrency()
                )
        );
        return response;
    }

    @PutMapping(path = "/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO debitAccountRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(
                new DebitAccountCommand(
                        debitAccountRequestDTO.getAccountId(),
                        debitAccountRequestDTO.getAmountDebited(),
                        debitAccountRequestDTO.getCurrency()
                )
        );
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping(path = "/eventStore/{accountId}")
    public Stream events(@PathVariable String accountId) {
        return eventStore.readEvents(accountId).asStream();
    }


}
