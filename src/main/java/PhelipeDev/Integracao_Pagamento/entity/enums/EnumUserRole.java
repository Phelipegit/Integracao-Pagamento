package PhelipeDev.Integracao_Pagamento.entity.enums;

import lombok.Getter;

@Getter
public enum EnumUserRole {
    DEFAULT("USER_DEFAULT"),ADMIN("USER_ADMIN");

    private String role;

    EnumUserRole(String role) {
        this.role = role;
    }
}
