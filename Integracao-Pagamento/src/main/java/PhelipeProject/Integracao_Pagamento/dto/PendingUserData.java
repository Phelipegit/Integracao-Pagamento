package PhelipeProject.Integracao_Pagamento.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PendingUserData {
    private String id;

    private String email;

    private String cpf;

    private String password;

    public PendingUserData(String email,String cpf,String password) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }
}
