package pe.com.bank.product.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeneralReportDTO {
	
	private String productId;
	private String productName;
	private List<InfoGeneralAccount> accountList;
	private List<InfoGeneralCredit> creditList;
	private List<InfoGeneralLoan> loanList;
		
}
