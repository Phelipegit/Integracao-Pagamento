package PhelipeProject.Integracao_Pagamento.dto.statusTransaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetStatusTransactionRecord(String external_reference,String date_last_update, String status) {
}
