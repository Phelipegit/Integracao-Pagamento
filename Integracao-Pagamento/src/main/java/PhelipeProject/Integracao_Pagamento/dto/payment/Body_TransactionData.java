package PhelipeProject.Integracao_Pagamento.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Body_TransactionData(String qr_code_base64,String qr_code) {
}
