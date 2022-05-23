package pe.com.bank.product.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.bank.product.client.entity.TransactionEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoGeneralLoan {
	
	private String loanId;
    private Double debt;
    private Date paymentDate;
    private Date endDate;
    private int quota;
    private String debtStatus;
    private int pendingQuota;
    private String customerId;
    private List<TransactionEntity> transactionList;

}
