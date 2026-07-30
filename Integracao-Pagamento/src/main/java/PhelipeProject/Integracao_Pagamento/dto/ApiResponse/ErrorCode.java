package PhelipeProject.Integracao_Pagamento.dto.ApiResponse;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorCode {

    private HttpStatus status;

    private String error;

    public ErrorCode(HttpStatus status,String error) {
        this.status = status;
        this.error = error;
    }
}
