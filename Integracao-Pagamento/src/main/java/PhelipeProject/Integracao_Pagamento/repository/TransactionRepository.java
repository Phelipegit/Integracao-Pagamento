package PhelipeProject.Integracao_Pagamento.repository;

import PhelipeProject.Integracao_Pagamento.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
    Optional<TransactionEntity> findByIdTransaction(String idTransaction);
}
