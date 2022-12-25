package com.example.accountManage.commands.aggregates;


import com.example.accountManage.commonapi.commands.CreateAccountCommand;
import com.example.accountManage.commonapi.commands.CreditAccountCommand;
import com.example.accountManage.commonapi.commands.DebitAccountCommand;
import com.example.accountManage.commonapi.enums.AccountStatus;
import com.example.accountManage.commonapi.events.AccountActivatedEvent;
import com.example.accountManage.commonapi.events.AccountCreatedEvent;
import com.example.accountManage.commonapi.events.AccountCreditedEvent;
import com.example.accountManage.commonapi.events.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate() {
        //Required by Axon
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        if (command.initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getInitialBalance(),
                command.getCurrency(),
                AccountStatus.CREATED
        ));
    }

    @EventSourcingHandler // evolution de l'etat de l'aggregate
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.status = AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(this.accountId, AccountStatus.ACTIVATED));
    }

    @EventSourcingHandler // evolution de l'etat de l'aggregate
    public void on(AccountActivatedEvent event) {
        this.status = event.getAccountStatus();
    }

    @CommandHandler
    public void handle(CreditAccountCommand command) {
        if (command.getAmountCredited() < 0) {
            throw new IllegalArgumentException("Amount to be credited cannot be negative");
        }
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmountCredited(),
                command.getCurrency()
        ));
    }

    @EventSourcingHandler // evolution de l'etat de l'aggregate
    public void on(AccountCreditedEvent event) {
        this.balance += event.getAmountCredited();
    }

    @CommandHandler
    public void handler(DebitAccountCommand command) {
        if (command.getAmountDebited() < 0) {
            throw new IllegalArgumentException("Amount to be debited cannot be negative");
        }
        if (this.balance - command.getAmountDebited() < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getAmountDebited(),
                command.getCurrency()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent accountDebitedEvent){
        this.balance -= accountDebitedEvent.amountDebited;
    }

}
