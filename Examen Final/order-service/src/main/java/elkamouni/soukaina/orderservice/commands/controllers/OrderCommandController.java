package elkamouni.soukaina.orderservice.commands.controllers;

import elkamouni.soukaina.examenFinalAPI.dtos.CreateCategorieRequestDTO;
import elkamouni.soukaina.examenFinalAPI.dtos.CreateOrderRequestDTO;
import elkamouni.soukaina.examenFinalAPI.events.CategorieCreatedEvent;
import elkamouni.soukaina.examenFinalAPI.events.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/command/order")
@AllArgsConstructor
@Service
public class OrderCommandController {
    private CommandGateway commandGateway;
    @PostMapping(path = "/create")
    public CompletableFuture<String> createOwner(@RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new OrderCreatedEvent(
                UUID.randomUUID().getMostSignificantBits(),
                createOrderRequestDTO.getCreatedAt(),
                createOrderRequestDTO.getStatus(),
                createOrderRequestDTO.getCustomerId()
        ));
        return response;
    }
}
