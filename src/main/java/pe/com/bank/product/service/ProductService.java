package pe.com.bank.product.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.bank.product.entity.ProductEntity;
import pe.com.bank.product.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.stream.Collectors;


@AllArgsConstructor
@Component
@Slf4j
public class ProductService {


    ProductRepository productRepository;

    public Mono<ServerResponse> getProducts(ServerRequest request) {
        /*        var creditId = request.queryParam("creditId");*/
        var reviewId = request.pathVariable("id");

        var existingReview = productRepository.findById(reviewId);
        return buildProductEntitysResponse(existingReview);


    }

    private Mono<ServerResponse> buildProductEntitysResponse(Mono<ProductEntity> reviewsFlux) {
        return ServerResponse.ok().body(reviewsFlux, ProductEntity.class);
    }


}
