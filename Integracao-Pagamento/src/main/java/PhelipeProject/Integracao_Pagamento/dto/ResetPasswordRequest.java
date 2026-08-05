package PhelipeProject.Integracao_Pagamento.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ResetPasswordRequest {

    public UUID id;

    public String passwordNew;

    public String confirmNewPassword;
}
