package elkamouni.soukaina.orderservice.Query.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullLigneCommande", types = ligneCommande.class)

public interface LigneCommandeProjection {
    public Long getId();
    public double getPrixUnite();
    public int getQuantity();
    public double getRemise();
}
