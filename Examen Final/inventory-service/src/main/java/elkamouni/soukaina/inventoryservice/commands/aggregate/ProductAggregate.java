package elkamouni.soukaina.inventoryservice.commands.aggregate;

import elkamouni.soukaina.examenFinalAPI.commands.CustomerCreatedCommand;
import elkamouni.soukaina.examenFinalAPI.commands.ProductCreatedCommand;
import elkamouni.soukaina.examenFinalAPI.events.CustomerCreatedEvent;
import elkamouni.soukaina.examenFinalAPI.events.ProductCreatedEvent;
import elkamouni.soukaina.inventoryservice.query.ENUM.ProductStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

public class ProductAggregate {
    @AggregateIdentifier
    private Long id;
    private String name;
    private double price;
    private int quantity;
    public ProductStatus productStatus;

    public ProductAggregate(){}

    @CommandHandler
    public ProductAggregate(ProductCreatedCommand command){
        if (command.getName() == null || command.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getName(),
                command.getPrice(),
                command.getQuantity(),
                command.getProductStatus()));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.productStatus=event.getProductStatus();
    }
}
