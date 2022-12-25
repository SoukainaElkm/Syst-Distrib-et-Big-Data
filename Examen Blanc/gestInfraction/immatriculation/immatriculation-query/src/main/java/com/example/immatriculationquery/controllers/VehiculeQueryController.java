package com.example.immatriculationquery.controllers;

import com.example.immatriculationquery.entities.Owner;
import com.example.immatriculationquery.entities.Vehicule;
import com.example.projectAPI.dtos.InfractionResponseDTO;
import com.example.projectAPI.queries.GetInfractionsByVehicle;
import com.example.projectAPI.queries.GetVehicule;
import com.example.projectAPI.queries.GetVehicules;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/vehicule")
@AllArgsConstructor
@Service
public class VehiculeQueryController {
    private QueryGateway queryGateway;


    @GetMapping(path = "/")
    public List<Vehicule> getVehicules() {
        return queryGateway.query(new GetVehicules(), ResponseTypes.multipleInstancesOf(Vehicule.class)).join();
    }

    @GetMapping(path = "/{id}")
    public Owner getVehicule(@PathVariable String id) {
        return queryGateway.query(new GetVehicule(id), Owner.class).join();
    }

    @GetMapping(path = "/byMartricule/{matricule}")
    public List<InfractionResponseDTO> getVehiculeByMatricule(@PathVariable String matricule) {
        return queryGateway.query(new GetInfractionsByVehicle(matricule), ResponseTypes.multipleInstancesOf(InfractionResponseDTO.class)).join();
    }
}
