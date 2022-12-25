package com.example.radarquery.controllers;

import com.example.projectAPI.queries.FindAllRadars;
import com.example.radarquery.entities.Radar;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/radar")
@AllArgsConstructor
public class RadarRestController {
    private QueryGateway queryGateway;

    @GetMapping("/all")
    public List<Radar> getAll(){
        return queryGateway.query(new FindAllRadars(), ResponseTypes.multipleInstancesOf(Radar.class)).join();
    }


}
