package pe.com.bank.product.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.bank.product.dto.GeneralReportDTO;
import pe.com.bank.product.entity.ProductEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
	
	 public Mono<ServerResponse> getProducts(ServerRequest request);
	 
	 public Mono<ServerResponse> getAllProducts();
	 
	 public Mono<ServerResponse> addProduct(ServerRequest request);
	 
	 public Mono<ServerResponse> updateProduct(ServerRequest request);

	 public Mono<ServerResponse> deleteProduct(ServerRequest request);
	 
	 public Mono<ServerResponse> getGeneralReport(ServerRequest request);
}
		