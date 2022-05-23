package pe.com.bank.product.client;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.bank.product.client.entity.TransactionEntity;
import reactor.core.publisher.Flux;


@Component
public class TrasanctionRestClient {
	
	
	private WebClient webClient;		
	  
	  public TrasanctionRestClient(WebClient webClient) {
	        this.webClient = webClient;
	    }
	  
	  
	  @Value("${restClient.transactionUrl}")
	  private String transactionUrl;
	  
	  
	  public Flux<TransactionEntity> getAccountByProductId(String productId){
		  var url = transactionUrl.concat("v1/transactions/productId/{id}");
		  
		  return  webClient
	                .get()
	                .uri(url,productId)
	                .retrieve()
	                .bodyToFlux(TransactionEntity.class)
	                .log();
	  }
	  
	  
	  public Flux<TransactionEntity> getTransactionsByDateAndAccountId(String accoundId,Date starDate,Date endDate){
		  var url = transactionUrl.concat("/v1/transactions/accountId/{accoundId}/betweenDates/01-05-2022/31-05-2022");
		  
		  return  webClient
	                .get()
	                .uri(url,accoundId)
	                .retrieve()
	                .bodyToFlux(TransactionEntity.class)
	                .log();
	  }
	  
	  public Flux<TransactionEntity> getTransactionsByDateAndCreditId(String creditId,Date starDate,Date endDate){
		  var url = transactionUrl.concat("/v1/transactions/creditId/{creditId}/betweenDates/01-05-2022/31-05-2022");
		  
		  return  webClient
	                .get()
	                .uri(url,creditId)
	                .retrieve()
	                .bodyToFlux(TransactionEntity.class)
	                .log();
	  }
	  
	  public Flux<TransactionEntity> getTransactionsByDateAndLoanId(String loanId,Date starDate,Date endDate){
		  var url = transactionUrl.concat("/v1/transactions/loandId/{loandId}/betweenDates/01-05-2022/31-05-2022");
		  
		  return  webClient
	                .get()
	                .uri(url,loanId)
	                .retrieve()
	                .bodyToFlux(TransactionEntity.class)
	                .log();
	  }  

}
