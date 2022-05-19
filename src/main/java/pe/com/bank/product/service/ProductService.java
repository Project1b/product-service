package pe.com.bank.product.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.bank.product.entity.ProductEntity;
import pe.com.bank.product.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
@Slf4j
public class ProductService {
    ProductRepository productRepository;

    public Mono<ServerResponse> getProducts(ServerRequest request) {
        var productId = request.pathVariable("id");
        var existingReview = productRepository.findById(productId);
        return buildProductEntitysResponse(existingReview);
    }

    private Mono<ServerResponse> buildProductEntitysResponse(Mono<ProductEntity> reviewsFlux) {
        return ServerResponse.ok().body(reviewsFlux, ProductEntity.class);
    }

    public Mono<ServerResponse> getAllProducts() {
        var existingReview2 = productRepository.findAll();
        return buildProductEntitysResponse(existingReview2);
    }

    private Mono<ServerResponse> buildProductEntitysResponse(Flux<ProductEntity> reviewsFlux) {
        return ServerResponse.ok().body(reviewsFlux, ProductEntity.class);
    }

    public Mono<ServerResponse> addProduct(ServerRequest request) {
        return request.bodyToMono(ProductEntity.class)
                .flatMap(productRepository::save)
                .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
    }

    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        var productId = request.pathVariable("id");
        var existingReview = productRepository.findById(productId);
        return existingReview
                .flatMap(product -> request.bodyToMono(ProductEntity.class)
                        .map(reqReview -> {
                            product.setType(reqReview.getType());
                            product.setProductName(reqReview.getProductName());
                            product.setCommission(reqReview.getCommission());
                            product.setTransactionLimit(reqReview.getTransactionLimit());
                            return product;
                        })
                        .flatMap(productRepository::save)
                        .flatMap(savedReview -> ServerResponse.ok().bodyValue(savedReview)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        var productId = request.pathVariable("id");
        var existingReview = productRepository.findById(productId);
        return existingReview
                .flatMap(review -> productRepository.deleteById(productId)
                        .then(ServerResponse.noContent().build()));
    }

}