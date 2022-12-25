package com.example.immatriculationquery.controllers;

import com.example.immatriculationquery.entities.Owner;
import com.example.immatriculationquery.entities.Vehicule;
import com.example.projectAPI.dtos.InfractionResponseDTO;
import com.example.projectAPI.queries.GetInfractionsByVehicle;
import com.example.projectAPI.queries.GetOwner;
import com.example.projectAPI.queries.GetOwners;
import com.example.projectAPI.queries.GetVehiculesByOwnerId;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/query/owner")
@AllArgsConstructor
@Service
public class OwnerQueryController {
    private QueryGateway queryGateway;


    @GetMapping(path = "/")
    public List<Owner> getOwner() {
        return queryGateway.query(new GetOwners(), ResponseTypes.multipleInstancesOf(Owner.class)).join();
    }

    @GetMapping(path = "/{id}")
    public Owner getOwner(@PathVariable String id) {
        return queryGateway.query(new GetOwner(id), Owner.class).join();
    }

    @GetMapping(path = "/infraction/{id}")
    public List<InfractionResponseDTO> getInfractionsByOwnerId(@PathVariable String id) {
        List<Vehicule> vehicules = queryGateway.query(new GetVehiculesByOwnerId(id), ResponseTypes.multipleInstancesOf(Vehicule.class)).join();
        List<InfractionResponseDTO> infractionResponseDTOS = new ArrayList<>();
        for (Vehicule vehicule : vehicules) {
            infractionResponseDTOS.addAll(queryGateway.query(new GetInfractionsByVehicle(vehicule.getMatricule()), ResponseTypes.multipleInstancesOf(InfractionResponseDTO.class)).join());
        }
        return infractionResponseDTOS;
    }
}
