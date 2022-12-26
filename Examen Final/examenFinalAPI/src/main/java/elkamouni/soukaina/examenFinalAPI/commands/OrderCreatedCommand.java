package elkamouni.soukaina.examenFinalAPI.commands;

import elkamouni.soukaina.orderservice.Query.enums.OrderStatus;
import lombok.Getter;

import java.util.Date;

public class OrderCreatedCommand extends BaseCommand<Long>{
    @Getter
    private Date createdAt;
    @Getter private OrderStatus status;
    @Getter private Long customerId;
    public OrderCreatedCommand(Long id, Date createdAt, OrderStatus status, Long customerId) {
        super(id);
        this.createdAt=createdAt;
        this.customerId=customerId;
        this.status=status;
    }
}
