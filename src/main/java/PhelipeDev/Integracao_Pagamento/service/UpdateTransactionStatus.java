package PhelipeDev.Integracao_Pagamento.service;

import PhelipeDev.Integracao_Pagamento.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTransactionStatus {

    private final TransactionRepository transactionRepository;

    public UpdateTransactionStatus(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void updateTransactionStatus() {

    }
}
