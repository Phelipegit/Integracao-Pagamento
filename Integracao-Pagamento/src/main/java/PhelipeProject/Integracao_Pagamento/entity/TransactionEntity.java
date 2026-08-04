package PhelipeProject.Integracao_Pagamento.entity;

import PhelipeProject.Integracao_Pagamento.entity.enums.EnumTransactionStatus;
import PhelipeProject.Integracao_Pagamento.entity.enums.EnumTypesPayments;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String idTransaction;

    @Column(nullable = false)
    @Getter
    private EnumTypesPayments type;

    @Column(precision = 10,scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Setter
    private EnumTransactionStatus status = EnumTransactionStatus.CREATED;

    @Column(nullable = false)
    private LocalDateTime creat_at;

    @Column(nullable = false)
    @Setter
    private LocalDateTime update_at = null;

    @ManyToOne
    private UserEntity id_user;

    public TransactionEntity(EnumTypesPayments type,String idTransaction,BigDecimal amount,UserEntity id_user) {
        this.type = type;
        this.idTransaction = idTransaction;
        this.amount = amount;
        this.creat_at = LocalDateTime.now(ZoneId.of("America/Campo_Grande"));
        this.id_user = id_user;
    }

    public TransactionEntity() {

    }
}
