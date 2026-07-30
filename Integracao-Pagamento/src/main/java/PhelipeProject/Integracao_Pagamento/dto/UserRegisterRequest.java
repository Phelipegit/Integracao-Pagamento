package PhelipeProject.Integracao_Pagamento.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
public class UserRegisterRequest {

    @Email
    @Size(max = 255)
    private String email;

    @CPF
    private String cpf;

    @Size(max = 255, min = 8)
    private String password;
}
