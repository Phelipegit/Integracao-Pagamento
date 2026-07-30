package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ApiResponse;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ErrorCode;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.TypesErrors;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.TypesSucess;
import PhelipeProject.Integracao_Pagamento.dto.PendingUserData;
import PhelipeProject.Integracao_Pagamento.dto.UserRegisterRequest;
import PhelipeProject.Integracao_Pagamento.entity.UserEntity;
import PhelipeProject.Integracao_Pagamento.repository.UserRepository;
import com.resend.core.exception.ResendException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRegisterService {

    private final UserRepository userRepository;
    private final RabbitComponent rabbitComponent;
    private final PasswordEncoder encoder;
    private final RedisService redis;

    private UserRegisterService(UserRepository userRepository, RabbitComponent rabbitComponent,PasswordEncoder encoder,RedisService redis) {
        this.userRepository = userRepository;
        this.rabbitComponent = rabbitComponent;
        this.encoder = encoder;
        this.redis = redis;
    }

    public ResponseEntity<ApiResponse<String>> userRegister(UserRegisterRequest request) throws ResendException {

        Optional<UserEntity> existEmail = userRepository.findByEmail(request.getEmail().trim().toLowerCase());

        if(existEmail.isPresent()) {
            return ResponseEntity.status(409).body(new ApiResponse<>(false,null,new ErrorCode(HttpStatus.CONFLICT, TypesErrors.EMAIL_ALREADY_IN_USE.name())));
        }

        Optional<UserEntity> existCpf = userRepository.findByCpf(request.getCpf().trim().toLowerCase());

        if(existCpf.isPresent()) {
            return ResponseEntity.status(409).body(new ApiResponse<>(false, null, new ErrorCode(HttpStatus.CONFLICT,TypesErrors.CPF_ALREADY_IN_USE.name())));
        }

        if(redis.exist_account_verify(request.getEmail().trim().toLowerCase())) {
            return ResponseEntity.status(409).body(new ApiResponse<>(false,null, new ErrorCode(HttpStatus.CONFLICT,TypesErrors.VERIFICATION_EMAIL_ALREADY_SENT.name())));
        }

        PendingUserData dataUser = new PendingUserData(request.getEmail().trim().toLowerCase(),
                request.getCpf().trim(),
                encoder.encode(request.getPassword()));

        redis.add("active-account:" + dataUser.getEmail(),dataUser,10L);

        rabbitComponent.handleActive_account(request.getEmail().trim().toLowerCase(),
                "Verifique seu e-mail",
                "<h1>Clique no link abaixo para verificar sua conta</h1>" +
                        "<strong>Seu link expira em 10 minutos</strong>" +
                        "<p>https://phelipedev.com.br/verify/active-account/" + dataUser.getId() + "/?email=" + dataUser.getEmail() + "</p>");

        return ResponseEntity.status(201).body(new ApiResponse<>(true, TypesSucess.EMAIL_SENT_VERIFICATION_ACCOUNT.name(),null));
    }
}
