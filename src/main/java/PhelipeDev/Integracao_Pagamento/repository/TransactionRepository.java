package PhelipeDev.Integracao_Pagamento.repository;

import PhelipeDev.Integracao_Pagamento.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository  extends JpaRepository<TransactionEntity, UUID> {

}
