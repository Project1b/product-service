package pe.com.bank.product.client.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEntity {

	private String transactionId;
	private Double amount;
	private Double commission;
	private Date date;
	private String type;
	private String typeDetails;
	private Double commissionTr;
	private String accountId;
	private String creditId;
	private String loanId;
	
}
