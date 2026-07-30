package PhelipeProject.Integracao_Pagamento.service;

import com.resend.core.exception.ResendException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitComponent {

    private final ResendService resendService;

    public RabbitComponent(ResendService resendService) {
        this.resendService = resendService;
    }

    @RabbitListener(queues = "${EMAIL_QUEUE_TRANSACTIONS}")
    public void handleTransactionEmail(String to,String subject,String html) throws ResendException {
        System.out.println("Consumindo a fila");
        resendService.pushEmail(to,subject,html);
    }

    @RabbitListener(queues = "${EMAIL_QUEUE_ACTIVE_ACCOUNT}")
    public void handleActive_account(String to,String subject,String html) throws ResendException {
        System.out.println("Consumindo a fila");
        resendService.pushEmail(to,subject,html);
    }
}
