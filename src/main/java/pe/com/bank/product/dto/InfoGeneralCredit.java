package pe.com.bank.product.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.bank.product.client.entity.TransactionEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoGeneralCredit {
	
	private String creditId;
    private Double amountUsed;
    private Double limitCredit;
    private Double creditAvailable;
    private String numberCredit;
    private String type;
    private String customerId;
    private List<TransactionEntity> transactionList;
	

}
