package pe.com.bank.product.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.bank.product.entity.ProductEntity;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {


}
