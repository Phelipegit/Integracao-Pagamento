package PhelipeProject.Integracao_Pagamento.service.transaction;

import PhelipeProject.Integracao_Pagamento.dto.statusTransaction.GetStatusTransactionRecord;
import PhelipeProject.Integracao_Pagamento.dto.statusTransaction.UpdateStatusTransactionRequest;
import PhelipeProject.Integracao_Pagamento.entity.TransactionEntity;
import PhelipeProject.Integracao_Pagamento.entity.enums.EnumTransactionStatus;
import PhelipeProject.Integracao_Pagamento.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class UStatusTransactionService {

    @Value("${URL_API_MERCADOPAGO}")
    private String url_mercadoPago;

    private final TransactionRepository transactionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UStatusTransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<HttpStatus> updateStatusTransaction(UpdateStatusTransactionRequest request) {

        Optional<TransactionEntity> entityOptional = transactionRepository.findByIdTransaction(request.getData().getId());

        if(entityOptional.isEmpty()) {
            return ResponseEntity.status(400).body(HttpStatus.BAD_REQUEST);
        }

        try {
            HttpClient httpClient = HttpClient.newBuilder().build();

            HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(url_mercadoPago + "payments/" + request.getData().getId())).build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            GetStatusTransactionRecord infoTransaction = objectMapper.convertValue(response, GetStatusTransactionRecord.class);

            TransactionEntity transactionEntity = entityOptional.get();

            transactionEntity.setStatus(EnumTransactionStatus.valueOf(infoTransaction.status()));
            transactionEntity.setUpdate_at(LocalDateTime.parse(infoTransaction.date_last_update()));

        }catch (Exception e) {
            System.out.println("quebrou status transaction request");
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(201).body(HttpStatus.OK);
    }
}
