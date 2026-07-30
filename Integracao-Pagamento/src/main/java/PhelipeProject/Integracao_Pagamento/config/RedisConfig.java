package PhelipeProject.Integracao_Pagamento.config;

import PhelipeProject.Integracao_Pagamento.dto.PendingUserData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, PendingUserData> pendingUserDataRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, PendingUserData> templet = new RedisTemplate<>();
        templet.setConnectionFactory(redisConnectionFactory);
        templet.setKeySerializer(new StringRedisSerializer());
        templet.setValueSerializer(new JacksonJsonRedisSerializer<>(PendingUserData.class));
        templet.afterPropertiesSet();
        return templet;
    }
}