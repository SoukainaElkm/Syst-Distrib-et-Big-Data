package elkamouni.soukaina.orderservice.Query.web;

import elkamouni.soukaina.orderservice.Query.entities.Order;
import elkamouni.soukaina.orderservice.Query.model.Customer;
import elkamouni.soukaina.orderservice.Query.model.Product;
import elkamouni.soukaina.orderservice.Query.repositories.OrderRepository;
import elkamouni.soukaina.orderservice.Query.repositories.ProductItemRepository;
import elkamouni.soukaina.orderservice.Query.services.CustomerRestClientService;
import elkamouni.soukaina.orderservice.Query.services.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProducts().forEach(pi->{
            Product product=inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }
}
