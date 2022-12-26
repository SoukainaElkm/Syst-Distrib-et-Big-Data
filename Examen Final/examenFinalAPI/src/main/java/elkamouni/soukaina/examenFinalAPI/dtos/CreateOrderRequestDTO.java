package elkamouni.soukaina.examenFinalAPI.dtos;

import elkamouni.soukaina.orderservice.Query.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequestDTO {
    private Date createdAt;
    private OrderStatus status;
    private Long customerId;
}
