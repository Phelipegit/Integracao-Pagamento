package PhelipeProject.Integracao_Pagamento.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderInfoRecord(String id,String type,String created_date,String total_amount) {
}
