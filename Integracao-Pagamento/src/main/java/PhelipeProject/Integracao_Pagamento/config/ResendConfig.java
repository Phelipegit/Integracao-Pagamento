package PhelipeProject.Integracao_Pagamento.config;

import com.resend.Resend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResendConfig {

    @Value("${RESEND_APIKEY}")
    private String API_KEY;

    @Bean
    public Resend resend() {
        return new Resend(API_KEY);
    }
}
