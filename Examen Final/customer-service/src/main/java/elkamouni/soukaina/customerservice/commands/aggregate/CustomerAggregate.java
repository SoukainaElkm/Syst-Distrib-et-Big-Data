package elkamouni.soukaina.customerservice.commands.aggregate;

import elkamouni.soukaina.examenFinalAPI.commands.CustomerCreatedCommand;
import elkamouni.soukaina.examenFinalAPI.events.CustomerCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

public class CustomerAggregate {
    @AggregateIdentifier
    private Long id;
    private String name;
    private String email;
    private String tel;

    public CustomerAggregate() {
    }

    @CommandHandler
    public CustomerAggregate(CustomerCreatedCommand command){
        if (command.getName() == null || command.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getName(),
                command.getTel(),
                command.getEmail()));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.tel = event.getTel();
        this.email = event.getEmail();
    }
}
