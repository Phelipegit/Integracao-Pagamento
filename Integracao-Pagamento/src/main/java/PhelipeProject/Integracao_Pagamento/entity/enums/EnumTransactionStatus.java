package PhelipeProject.Integracao_Pagamento.entity.enums;

import lombok.Getter;

@Getter
public enum EnumTransactionStatus {

    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected"),
    cancelled("cancelled");

    private String status;

    EnumTransactionStatus(String status) {
        this.status = status;
    }
}
