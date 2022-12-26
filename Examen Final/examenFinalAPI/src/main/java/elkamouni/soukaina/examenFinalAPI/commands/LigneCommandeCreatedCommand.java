package elkamouni.soukaina.examenFinalAPI.commands;

import lombok.Getter;

public class LigneCommandeCreatedCommand extends BaseCommand<Long>{
    @Getter
    private int quantity;
    @Getter private double prixUnite;
    @Getter private double remise;
    public LigneCommandeCreatedCommand(Long id, int quantity, double prixUnite, double remise) {
        super(id);
        this.prixUnite=prixUnite;
        this.quantity=quantity;
        this.remise=remise;
    }
}
