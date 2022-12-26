package elkamouni.soukaina.orderservice.Query.entities;

import lombok.*;
import elkamouni.soukaina.orderservice.Query.enums.OrderStatus;
import elkamouni.soukaina.orderservice.Query.model.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Table(name = "orders") @Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private OrderStatus status;
    private Long customerId;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<ligneCommande> ligneCommandes;

    @OneToMany(mappedBy = "order")
    private List<ProductItem> products;

    public double getTotal(){
        double somme=0;
        for(ligneCommande pi:ligneCommandes){
            somme+=pi.getPrixUnite();
        }
        return somme;
    }
}