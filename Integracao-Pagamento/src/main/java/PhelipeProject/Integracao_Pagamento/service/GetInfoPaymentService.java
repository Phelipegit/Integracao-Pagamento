package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ApiResponse;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ErrorCode;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.TypesErrors;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.TypesSucess;
import PhelipeProject.Integracao_Pagamento.dto.payment.GetBodyPayment;
import PhelipeProject.Integracao_Pagamento.dto.payment.InfoPaymentResponse;
import PhelipeProject.Integracao_Pagamento.entity.TransactionEntity;
import PhelipeProject.Integracao_Pagamento.entity.UserEntity;
import PhelipeProject.Integracao_Pagamento.entity.enums.EnumTransactionStatus;
import PhelipeProject.Integracao_Pagamento.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GetInfoPaymentService {

    @Value("${URL_API_MERCADOPAGO}")
    private String URL_API;
    @Value("${ACESS_TOKEN_MERCADOPAGO}")
    private String TOKEN_BEARER;

    private ObjectMapper objectMapper = new ObjectMapper();
    private final SecurityContextHolderService securityContextHolderService;
    private final TransactionRepository transactionRepository;

    public GetInfoPaymentService(SecurityContextHolderService securityContextHolderService,TransactionRepository transactionRepository) {
        this.securityContextHolderService = securityContextHolderService;
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<ApiResponse<InfoPaymentResponse>> getInfoPayment(String id) throws IOException, InterruptedException {

        Optional<UserEntity> entityOptional = securityContextHolderService.getUser();

        if(entityOptional.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(false,null,new ErrorCode(TypesErrors.PAGAMENT_INFO_INVALID.getStatus(),TypesErrors.PAGAMENT_INFO_INVALID.name())));
        }

        UserEntity userEntity = entityOptional.get();

        Optional<TransactionEntity> entityTransaction =  transactionRepository.findByIdTransaction(id);

        if(entityTransaction.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse<>(false,null,new ErrorCode(TypesErrors.PAGAMENT_INFO_INVALID.getStatus(),TypesErrors.PAGAMENT_INFO_INVALID.name())));
        }

        TransactionEntity transactionEntity = entityTransaction.get();

        try(HttpClient httpClient = HttpClient.newBuilder().build()) {

            HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL_API + "payments/" + id))
                    .header("Content-Type","application/json")
                    .header("Authorization","Bearer " + TOKEN_BEARER)
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            GetBodyPayment getBodyPayment = objectMapper.convertValue(response, GetBodyPayment.class);

            if(LocalDateTime.now().isAfter(LocalDateTime.parse(getBodyPayment.date_of_expiration())) || !transactionEntity.getId_user().getId().equals(userEntity.getId())) {
                return ResponseEntity.status(400).body(new ApiResponse<>(false,null,new ErrorCode(TypesErrors.PAGAMENT_INFO_INVALID.getStatus(),TypesErrors.PAGAMENT_INFO_INVALID.name())));
            }

            InfoPaymentResponse infoPaymentResponse = new InfoPaymentResponse(getBodyPayment.transaction_data().qr_code_base64(),
                    getBodyPayment.transaction_data().qr_code(),
                    LocalDateTime.parse(getBodyPayment.date_of_expiration()));

            return ResponseEntity.status(200).body(new ApiResponse<>(true,infoPaymentResponse,null));

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
