package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.entity.TransactionEntity;
import PhelipeProject.Integracao_Pagamento.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
public class CreatePagamentService {

    @Value("${URL_API_MERCADOPAGO}")
    private String URL_API;
    @Value("${ACESS_TOKEN_MERCADOPAGO}")
    private String TOKEN_BEARER;
    public TransactionRepository transactionRepository;

    public CreatePagamentService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<HttpStatus> createPagament() throws URISyntaxException, IOException, InterruptedException {

        UUID idPayment = UUID.randomUUID();

        try {
            HttpClient httpClient = HttpClient.newBuilder().build();

            HttpRequest httpRequest = HttpRequest
                    .newBuilder()
                    .uri(new URI(URL_API + "orders"))
                    .header("Content-type","application/json")
                    .header("X-Idempotency-Key",idPayment.toString())
                    .header("Authorization","Bearer " + TOKEN_BEARER)
                    .POST(HttpRequest.BodyPublishers.ofString( bodyJson))
                    .timeout(Duration.ofMinutes(5))
                    .build();


            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        }catch(HttpTimeoutException e) {
            return ResponseEntity.status(408).body(HttpStatus.BAD_REQUEST);
            System.out.println(e.getMessage());
        }


        TransactionEntity transactionEntity = new TransactionEntity();


    }

    private String formatarBody() {
        x
    }
}
