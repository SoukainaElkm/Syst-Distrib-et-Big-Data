package elkamouni.soukaina.orderservice.Query.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ligneCommande") @Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ligneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private double prixUnite;
    private double remise;
    @Transient
    private ProductItem product;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;

    public double getAmount(){
        return this.product.getPrice()*this.product.getQuantity()*(1- this.product.getRemise());
    }
}
