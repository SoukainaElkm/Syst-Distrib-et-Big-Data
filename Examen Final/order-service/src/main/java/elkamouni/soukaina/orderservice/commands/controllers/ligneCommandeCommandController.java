package elkamouni.soukaina.orderservice.commands.controllers;

import elkamouni.soukaina.examenFinalAPI.dtos.CreateLigneCommandeRequestDTO;
import elkamouni.soukaina.examenFinalAPI.dtos.CreateOrderRequestDTO;
import elkamouni.soukaina.examenFinalAPI.events.LigneCommandeCreatedEvent;
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
@RequestMapping("/command/lignedecommande")
@AllArgsConstructor
@Service
public class ligneCommandeCommandController {
    private CommandGateway commandGateway;
    @PostMapping(path = "/create")
    public CompletableFuture<String> createOwner(@RequestBody CreateLigneCommandeRequestDTO createLigneCommandeRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new LigneCommandeCreatedEvent(
                UUID.randomUUID().getMostSignificantBits(),
                createLigneCommandeRequestDTO.getQuantity(),
                createLigneCommandeRequestDTO.getRemise(),
                createLigneCommandeRequestDTO.getRemise()
        ));
        return response;
    }
}
