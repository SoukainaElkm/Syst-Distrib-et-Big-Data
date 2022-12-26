package elkamouni.soukaina.orderservice.commands.aggregate;

import elkamouni.soukaina.examenFinalAPI.commands.LigneCommandeCreatedCommand;
import elkamouni.soukaina.examenFinalAPI.commands.ProductCreatedCommand;
import elkamouni.soukaina.examenFinalAPI.events.LigneCommandeCreatedEvent;
import elkamouni.soukaina.examenFinalAPI.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;

public class ligneCommandeAggregate {
    @AggregateIdentifier
    private Long id;
    private int quantity;
    private double prixUnite;
    private double remise;
    public ligneCommandeAggregate(){}

    @CommandHandler
    public ligneCommandeAggregate(LigneCommandeCreatedCommand command){
        if (command.getQuantity() == 0) {
            throw new IllegalArgumentException("Quantity cannot be empty");
        }
        AggregateLifecycle.apply(new LigneCommandeCreatedEvent(
                command.getId(),
                command.getQuantity(),
                command.getPrixUnite(),
                command.getRemise()));
    }

    @EventSourcingHandler
    public void on(LigneCommandeCreatedEvent event) {
        this.id = event.getId();
        this.quantity = event.getQuantity();
        this.prixUnite = event.getPrixUnite();
        this.remise = event.getRemise();
    }
}
