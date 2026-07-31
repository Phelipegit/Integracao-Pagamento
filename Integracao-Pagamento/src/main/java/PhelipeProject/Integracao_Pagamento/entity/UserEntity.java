package PhelipeProject.Integracao_Pagamento.entity;

import PhelipeProject.Integracao_Pagamento.entity.enums.EnumRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
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

    @Column
    private LocalDateTime creat_at;

    @Column(nullable = false)
    @Setter
    @Enumerated(EnumType.STRING)
    private EnumRoles enumRoles = EnumRoles.DEFAULT;

    @Column
    @Setter
    private Boolean isActive = true;

    @OneToMany(mappedBy = "id_user", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactionEntityList = new ArrayList<>();

    public UserEntity(String email, String cpf, String password) {
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.creat_at = LocalDateTime.now(ZoneId.of("America/Campo_Grande"));
    }

    public UserEntity() {

    }
}
