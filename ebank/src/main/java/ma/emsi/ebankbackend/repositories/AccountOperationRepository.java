package ma.emsi.ebankbackend.repositories;

import ma.emsi.ebankbackend.entities.AccountOperation;
import ma.emsi.ebankbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> findByBankAccountId(String accountId);
}
