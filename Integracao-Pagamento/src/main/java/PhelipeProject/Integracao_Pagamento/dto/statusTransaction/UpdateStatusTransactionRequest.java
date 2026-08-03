package PhelipeProject.Integracao_Pagamento.dto.statusTransaction;

import lombok.Getter;

@Getter
public class UpdateStatusTransactionRequest {
    private int id;

    private String type;

    private String action;

    private UpdateStatusTransactionData data;

    public UpdateStatusTransactionRequest() {

    }
}
