package PhelipeDev.Integracao_Pagamento.entity;

import PhelipeDev.Integracao_Pagamento.entity.enums.EnumUserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,nullable = false)
    @Email
    private String email;

    @Column(unique = true,nullable = false)
    @CPF
    private String cpf;

    @Column(nullable = false)
    @Setter
    private String password;

    @Setter
    private Boolean ativo = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private EnumUserRole role = EnumUserRole.DEFAULT;

    @Column(nullable = false)
    private LocalDateTime create_at;

    @OneToMany(mappedBy = "user_id")
    private List<TransactionEntity> transactionEntityList = new ArrayList<>();

    public UserEntity(String email, String cpf, String password) {
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.create_at = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    public UserEntity() {

    }
}
