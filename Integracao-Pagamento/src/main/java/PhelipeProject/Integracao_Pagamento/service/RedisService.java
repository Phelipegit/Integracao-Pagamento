package PhelipeProject.Integracao_Pagamento.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {

    public static final String KEY_INIT_ACTIVE_ACCOUNT = "active-account:";

    public <T> void add(RedisTemplate<String, T> redisTemplate,String key, T object ,Long duration) {
        redisTemplate.opsForValue().set(key,object, Duration.ofMinutes(duration));
    }

    public <T> void deletePendingUserData(RedisTemplate<String, T> redisTemplate,String key) {
        redisTemplate.delete(KEY_INIT_ACTIVE_ACCOUNT + key);
    }

    public <T> T getAccountVerificationByKey(RedisTemplate<String,T> redisTemplate,String key) {
        return redisTemplate.opsForValue().get(KEY_INIT_ACTIVE_ACCOUNT + key);
    }

    public <T> Long getExpirationTime(RedisTemplate<String, T> redisTemplate,String key) {
        return redisTemplate.getExpire(key);
    }

    public <T> boolean existKey(RedisTemplate<String, T> redisTemplate,String key) {
        return redisTemplate.hasKey(KEY_INIT_ACTIVE_ACCOUNT + key);
    }
}
