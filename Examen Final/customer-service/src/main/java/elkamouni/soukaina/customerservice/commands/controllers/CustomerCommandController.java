package elkamouni.soukaina.customerservice.commands.controllers;

import elkamouni.soukaina.examenFinalAPI.dtos.CreateCustumerRequestDTO;
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
@RequestMapping("/command/customer")
@AllArgsConstructor
@Service
public class CustomerCommandController {
    private CommandGateway commandGateway;
    @PostMapping(path = "/create")
    public CompletableFuture<String> createOwner(@RequestBody CreateCustumerRequestDTO createCustumerRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new CustomerCreatedEvent(
                UUID.randomUUID().getMostSignificantBits(),
                createCustumerRequestDTO.getName(),
                createCustumerRequestDTO.getTel(),
                createCustumerRequestDTO.getEmail()
        ));
        return response;
    }
}
