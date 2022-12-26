package elkamouni.soukaina.inventoryservice.commands.controllers;

import elkamouni.soukaina.examenFinalAPI.dtos.CreateCategorieRequestDTO;
import elkamouni.soukaina.examenFinalAPI.dtos.CreateProductRequestDTO;
import elkamouni.soukaina.examenFinalAPI.events.CategorieCreatedEvent;
import elkamouni.soukaina.examenFinalAPI.events.ProductCreatedEvent;
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
@RequestMapping("/command/product")
@AllArgsConstructor
@Service
public class ProductCommandController {
    private CommandGateway commandGateway;
    @PostMapping(path = "/create")
    public CompletableFuture<String> createOwner(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new ProductCreatedEvent(
                UUID.randomUUID().getMostSignificantBits(),
                createProductRequestDTO.getName(),
                createProductRequestDTO.getPrice(),
                createProductRequestDTO.getQuantity(),
                createProductRequestDTO.getProductStatus()
        ));
        return response;
    }
}
