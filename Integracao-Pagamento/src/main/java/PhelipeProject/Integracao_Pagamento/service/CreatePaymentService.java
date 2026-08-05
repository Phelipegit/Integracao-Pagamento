package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ApiResponse;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ErrorCode;
import PhelipeProject.Integracao_Pagamento.dto.payment.GetBodyPayment;
import PhelipeProject.Integracao_Pagamento.entity.TransactionEntity;
import PhelipeProject.Integracao_Pagamento.entity.UserEntity;
import PhelipeProject.Integracao_Pagamento.entity.enums.EnumTypesPayments;
import PhelipeProject.Integracao_Pagamento.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreatePaymentService {

    @Value("${URL_API_MERCADOPAGO}")
    private String URL_API;
    @Value("${ACESS_TOKEN_MERCADOPAGO}")
    private String TOKEN_BEARER;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TransactionRepository transactionRepository;
    private final SecurityContextHolderService securityContextHolderService;

    public CreatePaymentService(TransactionRepository transactionRepository, SecurityContextHolderService securityContextHolderService) {
        this.transactionRepository = transactionRepository;
        this.securityContextHolderService = securityContextHolderService;
    }

    public ResponseEntity<ApiResponse<String>> createPagament(BigDecimal valor) throws URISyntaxException, IOException, InterruptedException {

        Optional<UserEntity> userEntity = securityContextHolderService.getUser();

        if(userEntity.isEmpty()) {
            return ResponseEntity.status(402).body(new ApiResponse<>(false,
                    null,
                    new ErrorCode(HttpStatus.UNAUTHORIZED,
                            HttpStatus.UNAUTHORIZED.name())));
        }

        UserEntity user = userEntity.get();

        String idPayment = UUID.randomUUID().toString();

        try(HttpClient httpClient = HttpClient.newBuilder().build()) {
            HttpRequest httpRequest = HttpRequest
                    .newBuilder()
                    .uri(new URI(URL_API + "payments"))
                    .header("Content-type","application/json")
                    .header("X-Idempotency-Key",idPayment)
                    .header("Authorization","Bearer " + TOKEN_BEARER)
                    .POST(HttpRequest.BodyPublishers.ofString(formatarBody(valor,userEntity.get())))
                    .timeout(Duration.ofSeconds(20))
                    .build();

            HttpResponse<String> responseReq = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            GetBodyPayment getBody = objectMapper.convertValue(responseReq, GetBodyPayment.class);

            TransactionEntity transactionEntity = new TransactionEntity(EnumTypesPayments.PIX,
                    getBody.id(),
                    valor,user
                    );

            transactionRepository.save(transactionEntity);

            System.out.println("log valor:" + getBody.transaction_details().total_paid_amount());

            return ResponseEntity.status(201).body(new ApiResponse<>(true,getBody.id(),new ErrorCode(HttpStatus.CREATED,null)));

        }catch(HttpTimeoutException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(408).body(new ApiResponse<>(false,null, new ErrorCode(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.name())));
        }
    }

    private String formatarBody(BigDecimal valor,UserEntity entity) {

        return """
                {
                    "transaction_amount": %s,
                    "payment_method_id: "pix",
                    "date_of_expiration":%s
                    "payer": {
                        "email": %s,
                        "identification": {
                            "type": "CPF",
                            "number": %s
                        }
                    }"
                }
                """.formatted(valor, LocalDateTime.now().plusMinutes(30),entity.getEmail(),entity.getCpf());
    }
}
