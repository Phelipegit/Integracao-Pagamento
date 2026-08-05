package PhelipeProject.Integracao_Pagamento.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetBodyPayment(String id, String status, String date_of_expiration, Body_TransactionDetails transaction_details, Body_TransactionData transaction_data) {
}
