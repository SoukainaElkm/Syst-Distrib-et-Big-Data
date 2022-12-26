package elkamouni.soukaina.inventoryservice.query.entities;

import elkamouni.soukaina.inventoryservice.query.ENUM.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;
    public ProductStatus productStatus;
    @Transient
    private Categorie category;
}
