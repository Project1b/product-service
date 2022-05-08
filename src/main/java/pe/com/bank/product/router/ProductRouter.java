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
//test
        return route()
                .nest(path("/v1/reviews"), builder -> {
                    builder .GET("",request -> productService.getProducts(request));
                })
                .GET("/v1/helloworld",(request -> ServerResponse.ok().bodyValue("helloworld")))
                .build();


    }
}
