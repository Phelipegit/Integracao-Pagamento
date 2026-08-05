package PhelipeProject.Integracao_Pagamento.dto.payment;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;

@Getter
public class CreatePaymentRequest {

    @DecimalMin("1.00")
    @DecimalMax("2000.00")
    private Double valor;

}
