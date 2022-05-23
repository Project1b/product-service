package pe.com.bank.product.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {

    private String loanId;
    private Double debt;
    private Date paymentDate;
    private Date endDate;
    private int quota;
    private String debtStatus;
    private int pendingQuota;
    private String productId;
    private String customerId;

}
