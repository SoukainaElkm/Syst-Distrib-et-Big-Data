package elkamouni.soukaina.inventoryservice.commands.aggregate;

import elkamouni.soukaina.examenFinalAPI.commands.CategorieCreatedCommand;
import elkamouni.soukaina.examenFinalAPI.commands.ProductCreatedCommand;
import elkamouni.soukaina.examenFinalAPI.events.CategorieCreatedEvent;
import elkamouni.soukaina.examenFinalAPI.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

public class CategorieAggregate {
    @AggregateIdentifier
    private Long id;
    private String name;
    private String Description;
    public CategorieAggregate(){}

    @CommandHandler
    public CategorieAggregate(CategorieCreatedCommand command){
        if (command.getName() == null || command.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        AggregateLifecycle.apply(new CategorieCreatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription()));
    }

    @EventSourcingHandler
    public void on(CategorieCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.Description = event.getDescription();
    }
}
