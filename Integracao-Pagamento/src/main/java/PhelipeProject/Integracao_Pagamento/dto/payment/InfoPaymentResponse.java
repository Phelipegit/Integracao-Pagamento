package PhelipeProject.Integracao_Pagamento.dto.payment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InfoPaymentResponse {

    private String qr_code_base64;

    private String qr_code;

    private LocalDateTime date_of_expiration;


    public InfoPaymentResponse(String qr_code_base64,String qr_code,LocalDateTime date_of_expiration) {
        this.qr_code_base64 = qr_code_base64;
        this.qr_code = qr_code;
        this.date_of_expiration = date_of_expiration;
    }
}
