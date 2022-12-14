package com.example.infractioncommand.controllers;

import com.example.projectAPI.commands.CreateInfractionCommand;
import com.example.projectAPI.dtos.InfractionCreationRequestDTO;
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
@RequestMapping("/command/infraction")
@AllArgsConstructor
@Service
public class InfractionCommandController {
    private CommandGateway commandGateway;
    @PostMapping("/create")
    public CompletableFuture<String> create(@RequestBody InfractionCreationRequestDTO infractionCreationRequestDTO){
        CompletableFuture<String> response = commandGateway.send(new CreateInfractionCommand(
                UUID.randomUUID().toString(),
                infractionCreationRequestDTO.getMatricule(),
                infractionCreationRequestDTO.getSpeed(),
                infractionCreationRequestDTO.getDate(),
                infractionCreationRequestDTO.getRadarId(),
                infractionCreationRequestDTO.getRadarSpeed()
        ));
        return response;
    }
}
