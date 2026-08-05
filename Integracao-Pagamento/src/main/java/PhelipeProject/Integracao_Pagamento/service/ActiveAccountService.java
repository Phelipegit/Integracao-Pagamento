package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.ApiResponse;
import PhelipeProject.Integracao_Pagamento.dto.ApiResponse.TypesSucess;
import PhelipeProject.Integracao_Pagamento.dto.PendingUserData;
import PhelipeProject.Integracao_Pagamento.entity.UserEntity;
import PhelipeProject.Integracao_Pagamento.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ActiveAccountService {

    private final RedisTemplate<String,PendingUserData> redisTemplate;
    private final UserRepository userRepository;
    private final RedisService redis;

    public ActiveAccountService(RedisTemplate<String,PendingUserData> redisTemplate,UserRepository userRepository,RedisService redis) {
        this.redisTemplate = redisTemplate;
        this.userRepository = userRepository;
        this.redis = redis;
    }

    public ResponseEntity<ApiResponse<String>> activeAccount(String email, String uuid) {
        PendingUserData pendingUserData = redis.getAccountVerificationByKey(redisTemplate,email);

        if(pendingUserData == null || !pendingUserData.getId().equals(uuid)) {
            return ResponseEntity.status(404).body(new ApiResponse<>(false,null,null));
        }



        UserEntity userEntity = new UserEntity(pendingUserData.getEmail(),
                pendingUserData.getCpf(),
                pendingUserData.getPassword());

        userRepository.save(userEntity);

        redis.deletePendingUserData(redisTemplate,email);

        return ResponseEntity.status(201).body(new ApiResponse<>(true, TypesSucess.EMAIL_VERIFICATION_OK.name(), null));
    }
}
