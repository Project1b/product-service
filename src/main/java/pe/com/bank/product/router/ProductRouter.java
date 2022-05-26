package pe.com.bank.product.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.bank.product.service.ProductService;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.text.ParseException;

@Configuration
public class ProductRouter {

	
    @Bean
    public RouterFunction<ServerResponse> productsRoute(ProductService productService){
        return route()
                .nest(path("/v1/products"), builder -> builder
                        .GET("/getProduct/{id}", productService::getProducts)
                        .GET("/getAllProduct",request -> productService.getAllProducts())
                        .GET("/getGeneralReport/{startDate}/{endDate}", productService::getGeneralReport)
                        .POST("/createProduct", productService::addProduct)
                        .PUT("/updateProduct/{id}", productService::updateProduct)
                        .DELETE("/deleteProduct/{id}", productService::deleteProduct))
                .build();
    }
  
}