package elkamouni.soukaina.examenFinalAPI.dtos;

import elkamouni.soukaina.inventoryservice.query.ENUM.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDTO {
    private String name;
    private double price;
    private int quantity;
    public ProductStatus productStatus;
}
