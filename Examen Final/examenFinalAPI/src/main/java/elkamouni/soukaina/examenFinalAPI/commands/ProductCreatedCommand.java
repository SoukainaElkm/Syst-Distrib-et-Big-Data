package elkamouni.soukaina.examenFinalAPI.commands;

import elkamouni.soukaina.inventoryservice.query.ENUM.ProductStatus;
import lombok.Getter;

public class ProductCreatedCommand extends BaseCommand<Long>{
    @Getter
    private String name;
    @Getter
    private double price;
    @Getter
    private int quantity;
    @Getter
    public ProductStatus productStatus;
    public ProductCreatedCommand(Long id, String name, double price, int quantity, ProductStatus productStatus) {
        super(id);
        this.name= name;
        this.productStatus=productStatus;
        this.price=price;
        this.quantity=quantity;
    }
}
