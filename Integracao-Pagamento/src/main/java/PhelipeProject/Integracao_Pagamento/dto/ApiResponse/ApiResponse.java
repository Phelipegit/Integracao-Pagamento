package PhelipeProject.Integracao_Pagamento.dto.ApiResponse;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class ApiResponse<T> {
    private Boolean success;

    private T data;

    private ErrorCode error;

    private LocalDateTime localDateTime;

    public ApiResponse(Boolean success, T data, ErrorCode error) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.localDateTime = LocalDateTime.now(ZoneId.of("America/Campo_Grande"));
    }
}
