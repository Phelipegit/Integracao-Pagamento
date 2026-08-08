package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.entity.UserEntity;
import PhelipeProject.Integracao_Pagamento.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

public class SendEmailResetPasswordService {

    private UserRepository userRepository;
    private ResendService resendService;
    private RedisTemplate<String,Object> redisTemplate;
    private final String KEY_REDIS = "reset-password";

    public SendEmailResetPasswordService(UserRepository userRepository,ResendService resendService,RedisTemplate<String,Object> redisTemplate) {
        this.userRepository = userRepository;
        this.resendService = resendService;
        this.redisTemplate = redisTemplate;
    }

    public Object sendEmailResetPassword(String email) {

        



        return null;
    }
}
