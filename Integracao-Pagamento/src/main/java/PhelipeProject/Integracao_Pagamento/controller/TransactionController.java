package PhelipeProject.Integracao_Pagamento.controller;

import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ApiResponse;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ErrorCode;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.TypesErrors;
import PhelipeProject.Integracao_Pagamento.dto.payment.InfoPaymentResponse;
import PhelipeProject.Integracao_Pagamento.dto.statusTransaction.UpdateStatusTransactionRequest;
import PhelipeProject.Integracao_Pagamento.service.CreatePaymentService;
import PhelipeProject.Integracao_Pagamento.service.GetInfoPaymentService;
import PhelipeProject.Integracao_Pagamento.service.transaction.UStatusTransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/payments")
public class TransactionController {

    private final UStatusTransactionService uStatusTransactionService;
    private final GetInfoPaymentService getInfoPaymentService;
    private final CreatePaymentService createPaymentService;

    public TransactionController(UStatusTransactionService uStatusTransactionService, GetInfoPaymentService getInfoPaymentService ,CreatePaymentService createPaymentService) {
        this.uStatusTransactionService = uStatusTransactionService;
        this.getInfoPaymentService = getInfoPaymentService;
        this.createPaymentService = createPaymentService;
    }

    @PutMapping("/status/update")
    public ResponseEntity<HttpStatus> uStatusTransaction(UpdateStatusTransactionRequest request) {
        return uStatusTransactionService.updateStatusTransaction(request);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<ApiResponse<InfoPaymentResponse>> getInfoPayment(@PathVariable String id) throws IOException, InterruptedException {
        return getInfoPaymentService.getInfoPayment(id);
    }

    @PostMapping("/create")
    @Validated
    public ResponseEntity<ApiResponse<String>> createPayment(@RequestParam @DecimalMin(value = "1.00") @DecimalMax(value = "1999.99") Double amount) throws URISyntaxException,
            IOException,
            InterruptedException {

        BigDecimal amountBig = new BigDecimal(amount);


        return createPaymentService.createPagament(amountBig);
    }
}
