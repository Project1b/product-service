package pe.com.bank.product.router;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.com.bank.product.dto.GeneralReportDTO;
import pe.com.bank.product.service.ProductService;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@RestController
@RequestMapping("/v2")
public class ProductController {
	
/*	
	ProductService productService;
	
	@GetMapping ("/products/getGeneralReport/{startDate}/{endDate}")
	public Flux<GeneralReportDTO> getTransactionsByLoanId(@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy")Date startDate,
			@PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate){
		return productService.getGeneralReport(startDate,endDate);
	}
*/
}
