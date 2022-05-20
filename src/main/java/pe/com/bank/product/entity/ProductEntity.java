package pe.com.bank.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class ProductEntity {

    @Id
    private String productId;
    private String type;
    private String productName;
    private Double commissionMaintenance;
    private String transactionLimit;

}