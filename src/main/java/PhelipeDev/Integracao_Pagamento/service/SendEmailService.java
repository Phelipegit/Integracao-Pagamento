package PhelipeDev.Integracao_Pagamento.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    private final Resend resend;

    public SendEmailService(Resend resend) {
        this.resend = resend;
    }

    public void pushEmail(CreateEmailOptions createEmailOptions) throws ResendException {
        resend.emails().send(createEmailOptions);
    }
}
