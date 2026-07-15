package PhelipeDev.Integracao_Pagamento.entity;

import PhelipeDev.Integracao_Pagamento.entity.enums.EnumTransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;

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

    @Column(nullable = false,unique = true)
    private String transaction_id;

    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne
    private UserEntity user_id;

    @Column(nullable = false)
    private EnumTransactionStatus status;

    private LocalDateTime create_at;

    public TransactionEntity(String transaction_id,BigDecimal amount, UserEntity user_id,EnumTransactionStatus status) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.user_id = user_id;
        this.status = status;
        this.create_at = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    public TransactionEntity() {

    }
}
