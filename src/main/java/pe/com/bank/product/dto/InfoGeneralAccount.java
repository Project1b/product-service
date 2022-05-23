package pe.com.bank.product.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.bank.product.client.entity.TransactionEntity;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoGeneralAccount {
	
	private String accountId;
	private String accountNumber;
	private Double amount;
	private String dateOpen;
	private String amounttype;	
	private String customerId;
	private List<TransactionEntity> transactionList;

}
