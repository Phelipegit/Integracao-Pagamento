package PhelipeDev.Integracao_Pagamento.entity.enums;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public enum EnumTransactionStatus {
    APROVADA("Aprovada"),PROCESSAMENTO("Processamento"),REJEITADA("Rejeitada");

    @Enumerated(EnumType.STRING)
    private String status;

    EnumTransactionStatus(String status) {
        this.status = status;
    }
}
