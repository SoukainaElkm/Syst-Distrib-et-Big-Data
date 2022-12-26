package elkamouni.soukaina.examenFinalAPI.events;

import elkamouni.soukaina.orderservice.Query.enums.OrderStatus;
import lombok.Getter;

import java.util.Date;

public class OrderCreatedEvent extends BaseEvent<Long>{
    @Getter private Date createdAt;
    @Getter private OrderStatus status;
    @Getter private Long customerId;
    public OrderCreatedEvent(Long id, Date createdAt, OrderStatus status, Long customerId) {
        super(id);
        this.createdAt=createdAt;
        this.customerId=customerId;
        this.status=status;
    }
}
