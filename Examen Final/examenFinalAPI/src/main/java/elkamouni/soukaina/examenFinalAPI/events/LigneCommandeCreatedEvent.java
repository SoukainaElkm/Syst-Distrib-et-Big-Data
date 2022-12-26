package elkamouni.soukaina.examenFinalAPI.events;

import lombok.Getter;

public class LigneCommandeCreatedEvent extends BaseEvent<Long>{
    @Getter private int quantity;
    @Getter private double prixUnite;
    @Getter private double remise;
    public LigneCommandeCreatedEvent(Long id, int quantity, double prixUnite, double remise) {
        super(id);
        this.prixUnite=prixUnite;
        this.quantity=quantity;
        this.remise=remise;
    }
}
