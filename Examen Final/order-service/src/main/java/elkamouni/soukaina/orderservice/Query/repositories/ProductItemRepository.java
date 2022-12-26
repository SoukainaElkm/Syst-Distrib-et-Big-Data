package elkamouni.soukaina.orderservice.Query.repositories;

import elkamouni.soukaina.orderservice.Query.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
}
