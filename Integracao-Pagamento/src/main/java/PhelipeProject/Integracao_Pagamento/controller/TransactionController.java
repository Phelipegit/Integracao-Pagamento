package PhelipeProject.Integracao_Pagamento.controller;

import PhelipeProject.Integracao_Pagamento.dto.statusTransaction.UpdateStatusTransactionRequest;
import PhelipeProject.Integracao_Pagamento.service.transaction.UStatusTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final UStatusTransactionService uStatusTransactionService;

    public TransactionController(UStatusTransactionService uStatusTransactionService) {
        this.uStatusTransactionService = uStatusTransactionService;
    }

    @PutMapping("/status/update")
    public ResponseEntity<HttpStatus> uStatusTransaction(UpdateStatusTransactionRequest request) {
        return uStatusTransactionService.updateStatusTransaction(request);
    }

}
