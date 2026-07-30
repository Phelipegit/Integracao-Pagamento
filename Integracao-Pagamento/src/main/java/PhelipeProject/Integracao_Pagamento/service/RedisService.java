package PhelipeProject.Integracao_Pagamento.service;

import PhelipeProject.Integracao_Pagamento.dto.PendingUserData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {

    private final RedisTemplate<String, PendingUserData> redisTemplatePendingUserData;
    private static final String KEY_INIT_ACTIVE_ACCOUNT = "active-account:";

    public RedisService(RedisTemplate<String,PendingUserData> redisTemplate) {
        this.redisTemplatePendingUserData = redisTemplate;
    }

    public void add(String key, PendingUserData object,Long duration) {
        this.redisTemplatePendingUserData.opsForValue().set(key,object, Duration.ofMinutes(duration));
    }

    public void deletePendingUserData(String key) {
        this.redisTemplatePendingUserData.delete(KEY_INIT_ACTIVE_ACCOUNT + key);
    }

    public PendingUserData getAccountVerificationByKey(String key) {
        return redisTemplatePendingUserData.opsForValue().get(KEY_INIT_ACTIVE_ACCOUNT + key);
    }

    public Long getExpirationTime(String key) {
        return this.redisTemplatePendingUserData.getExpire(key);
    }

    public boolean exist_account_verify(String key) {
        return redisTemplatePendingUserData.hasKey(KEY_INIT_ACTIVE_ACCOUNT + key);
    }
}
