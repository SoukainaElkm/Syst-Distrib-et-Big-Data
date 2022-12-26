package elkamouni.soukaina.orderservice.commands.aggregate;

import elkamouni.soukaina.examenFinalAPI.commands.OrderCreatedCommand;
import elkamouni.soukaina.examenFinalAPI.commands.ProductCreatedCommand;
import elkamouni.soukaina.examenFinalAPI.events.OrderCreatedEvent;
import elkamouni.soukaina.examenFinalAPI.events.ProductCreatedEvent;
import elkamouni.soukaina.orderservice.Query.enums.OrderStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

import java.util.Date;

import static elkamouni.soukaina.orderservice.Query.enums.OrderStatus.CANCELED;
import static elkamouni.soukaina.orderservice.Query.enums.OrderStatus.DELIVERED;

public class OrderAggregate {
    @AggregateIdentifier
    private Long id;
    private Date createdAt;
    private OrderStatus status;
    private Long customerId;

    public OrderAggregate(){}

    @CommandHandler
    public OrderAggregate(OrderCreatedCommand command){
        if (command.getStatus() == CANCELED || command.getStatus() == DELIVERED) {
            throw new IllegalArgumentException("Order Delivered or Cnaceled");
        }
        AggregateLifecycle.apply(new OrderCreatedEvent(
                command.getId(),
                command.getCreatedAt(),
                command.getStatus(),
                command.getCustomerId()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.id = event.getId();
        this.createdAt = event.getCreatedAt();
        this.status = event.getStatus();
        this.customerId = event.getCustomerId();
    }
}
