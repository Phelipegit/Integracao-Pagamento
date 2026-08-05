package PhelipeProject.Integracao_Pagamento.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Body_TransactionDetails(Double total_paid_amount) {
}
