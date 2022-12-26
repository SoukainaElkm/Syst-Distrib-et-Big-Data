package elkamouni.soukaina.examenFinalAPI.events;

import elkamouni.soukaina.inventoryservice.query.ENUM.ProductStatus;
import lombok.Getter;

public class ProductCreatedEvent extends BaseEvent<Long>{

    @Getter
    private String name;
    @Getter
    private double price;
    @Getter
    private int quantity;
    @Getter
    public ProductStatus productStatus;

    public ProductCreatedEvent(Long id, String name, double price, int quantity, ProductStatus productStatus) {
        super(id);
        this.name= name;
        this.productStatus=productStatus;
        this.price=price;
        this.quantity=quantity;
    }
}
