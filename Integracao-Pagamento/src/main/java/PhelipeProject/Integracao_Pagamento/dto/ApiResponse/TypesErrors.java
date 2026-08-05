package PhelipeProject.Integracao_Pagamento.dto.ApiResponse;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TypesErrors {

    PASSWORD_TOO_SHORT("PASSWORD_TOO_SHORT",HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_LONG("PASSWORD_TOO_LONG",HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_IN_USE("EMAIL_ALREADY_IN_USE",HttpStatus.CONFLICT),
    CPF_ALREADY_IN_USE("CPF_ALREADY_IN_USE",HttpStatus.CONFLICT),
    VERIFICATION_EMAIL_ALREADY_SENT("VERIFICATION_EMAIL_ALREADY_SENT",HttpStatus.CONFLICT),
    INVALIDS_CREDENTIALS("INVALIDS_CREDENTIALS",HttpStatus.UNAUTHORIZED),
    AMOUNT_INVALID("AMOUNT_INVALID",HttpStatus.BAD_REQUEST),
    PAGAMENT_INFO_INVALID("PAGAMENT_INFO_INVALID",HttpStatus.BAD_REQUEST);

    private String error;

    private HttpStatus status;

    TypesErrors(String error,HttpStatus status) {
        this.error = error;
        this.status = status;
    }
}
