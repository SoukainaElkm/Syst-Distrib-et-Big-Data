package elkamouni.soukaina.inventoryservice.commands.controllers;

import elkamouni.soukaina.examenFinalAPI.dtos.CreateCategorieRequestDTO;
import elkamouni.soukaina.examenFinalAPI.dtos.CreateCustumerRequestDTO;
import elkamouni.soukaina.examenFinalAPI.events.CategorieCreatedEvent;
import elkamouni.soukaina.examenFinalAPI.events.CustomerCreatedEvent;
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
@RequestMapping("/command/categorie")
@AllArgsConstructor
@Service
public class CategorieCommandController {
    private CommandGateway commandGateway;
    @PostMapping(path = "/create")
    public CompletableFuture<String> createOwner(@RequestBody CreateCategorieRequestDTO createCategorieRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new CategorieCreatedEvent(
                UUID.randomUUID().getMostSignificantBits(),
                createCategorieRequestDTO.getName(),
                createCategorieRequestDTO.getDescription()
        ));
        return response;
    }
}
