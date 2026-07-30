package PhelipeProject.Integracao_Pagamento.entity.enums;

import lombok.Getter;

@Getter
public enum EnumTransactionStatus {

    CREATED("CRIADA"),
    ACTION_REQUIRED("ESPERANDO PAGAMENTO"),
    PROCESSED("PROCESSADA"),
    PROCESSING("EM PROCESSAMENTO"),
    EXPIRED("EXPIRADA"),
    FAILED("FALHADA"),
    REFUNDED("REEMBOLSADA"),
    CHARGED_BACK("EM CONTESTAÇÃO");

    private String status;

    EnumTransactionStatus(String status) {
        this.status = status;
    }
}
