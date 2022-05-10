package pe.com.bank.product.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.bank.product.service.ProductService;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> productsRoute(ProductService productService){

        return route()
                .nest(path("/v1/products"), builder -> {
                    builder .GET("/{id}",request -> productService.getProducts(request))
                            .GET("",request ->productService.getAllProducts(request))
                            .POST("",request -> productService.addProduct(request))
                            .PUT("/{id}", request -> productService.updateProduct(request))
                            .DELETE("/{id}", request -> productService.deleteProduct(request))
                    .GET("/{id}", request -> productService.getProducts(request));
                })
                .GET("/v1/hello",(request -> ServerResponse.ok().bodyValue("hello")))
                .build();


    }
}
