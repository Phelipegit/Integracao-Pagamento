package PhelipeProject.Integracao_Pagamento.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.stereotype.Service;

@Service
public class ResendService {

    private final Resend resend;

    public ResendService(Resend resend) {
        this.resend = resend;
    }

    public void pushEmail(String to,String subject,String html) throws ResendException {
        CreateEmailOptions options = CreateEmailOptions
                .builder()
                .from("noreply@phelipedev.com.br")
                .to(to)
                .subject(subject)
                .html(html)
                .build();

        resend.emails().send(options);
    }
}
