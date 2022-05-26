package pe.com.bank.product.service;

import lombok.AllArgsConstructor;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.bank.product.client.AccountRestClient;
import pe.com.bank.product.client.CreditRestClient;
import pe.com.bank.product.client.LoanRestClient;
import pe.com.bank.product.client.TrasanctionRestClient;
import pe.com.bank.product.dto.GeneralReportDTO;
import pe.com.bank.product.dto.InfoGeneralAccount;
import pe.com.bank.product.dto.InfoGeneralCredit;
import pe.com.bank.product.dto.InfoGeneralLoan;
import pe.com.bank.product.entity.ProductEntity;
import pe.com.bank.product.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class ProductServiceImpl implements ProductService {
	
	
	
    ProductRepository productRepository;
    AccountRestClient accountRestClient;
    CreditRestClient creditRestClient;
    LoanRestClient loanRestClient;
    TrasanctionRestClient trasanctionRestClient;

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
                            product.setCommissionMaintenance(reqReview.getCommissionMaintenance());
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
    
    
	public Mono<ServerResponse> getGeneralReport(ServerRequest request){
				
		var startDate =request.pathVariable("startDate");
		var endDate = request.pathVariable("endDate");

		

		var generalReport = productRepository.findAll().flatMap( product -> {
						
			 var acountInfo = accountRestClient.getAccountByProductId(product.getProductId()).flatMap(account ->  {				
				 var transactionsAccount = trasanctionRestClient.getTransactionsByDateAndAccountId(account.getId(),startDate,endDate).collectList();
				 
				 return transactionsAccount.map(t ->  new InfoGeneralAccount(account.getId(),account.getAccountNumber(),account.getAmount(),
						 account.getDateOpen(),account.getAmounttype(),account.getCustomerId(),t));
									
			}).collectList();
			 
			var creditInfo = creditRestClient.getCreditByProductId(product.getProductId()).flatMap( credit -> {			
				 var transactionsCredit = trasanctionRestClient.getTransactionsByDateAndCreditId(credit.getCreditId(),startDate,endDate).collectList();
				 	
				 return transactionsCredit.map(t -> new InfoGeneralCredit(credit.getCreditId(),credit.getAmountUsed(),credit.getLimitCredit(),
						 credit.getCreditAvailable(),credit.getNumberCredit(),credit.getType(),credit.getCustomerId(),t));
									 
			}).collectList();
			
			var loanInfo = loanRestClient.getLoanByProductId(product.getProductId()).flatMap( loan -> {
				var transactionsLoan = trasanctionRestClient.getTransactionsByDateAndLoanId(loan.getLoanId(), startDate,endDate).collectList();
							
				return transactionsLoan.map( t -> new InfoGeneralLoan(loan.getLoanId(),loan.getDebt(),loan.getPaymentDate(),loan.getEndDate(),
						loan.getQuota(),loan.getDebtStatus(),loan.getPendingQuota(),loan.getCustomerId(),t));
			}).collectList();			
			
			return acountInfo.flatMap( a ->  {
				
				return creditInfo.flatMap( c -> {
					
					return loanInfo.flatMap( l -> {
						return Mono.just(new GeneralReportDTO(product.getProductId(),product.getProductName(),a,c,l));
					});
				});
				
			});
		});
		
			return buildGeneralReportResponse(generalReport);
		 
		// return buildGeneralReportResponse(Flux.empty());
	}

	
	private Mono<ServerResponse> buildGeneralReportResponse(Flux<GeneralReportDTO> generalReportFlux) {
        return ServerResponse.ok().body(generalReportFlux, GeneralReportDTO.class);
    }

	
	
}