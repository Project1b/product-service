package pe.com.bank.product.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bank.product.entity.ProductEntity;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {

}