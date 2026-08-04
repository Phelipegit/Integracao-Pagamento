package PhelipeProject.Integracao_Pagamento.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetBodyCreatePayment(BigDecimal transaction_amount,String status) {
}
