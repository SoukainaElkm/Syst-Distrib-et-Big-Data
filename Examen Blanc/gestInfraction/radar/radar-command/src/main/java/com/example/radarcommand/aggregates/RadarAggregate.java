package com.example.radarcommand.aggregates;

import com.example.projectAPI.commands.CreateRadarCommand;
import com.example.projectAPI.commands.PassedVehiculeRadarCommand;
import com.example.projectAPI.events.RadarCatchSpeedEvent;
import com.example.projectAPI.events.RadarCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class RadarAggregate {
    @AggregateIdentifier
    private String radarId;
    private double maxSpeed;
    private double latitude;
    private double longitude;

    public RadarAggregate() {
        //Required by Axon
    }

    @CommandHandler
    public RadarAggregate(CreateRadarCommand command){
        if (command.maxSpeed < 0) {
            throw new IllegalArgumentException("Max Speed cannot be negative");
        }
        AggregateLifecycle.apply(new RadarCreatedEvent(
                command.getId(),
                command.getMaxSpeed(),
                command.getLatitude(),
                command.getLongitude()));
    }

    @CommandHandler
    public RadarAggregate(PassedVehiculeRadarCommand command){
        AggregateLifecycle.apply(new RadarCatchSpeedEvent(
                command.getId(),
                command.getMatricule(),
                command.getVehicleSpeed(),
                command.getRadarId(),
                command.getRadarSpeed()
                ));
    }

    @EventSourcingHandler //change the state of the aggregate
    public void on(RadarCreatedEvent event){
        this.radarId = event.getId();
        this.maxSpeed = event.getMaxSpeed();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
    }
}
