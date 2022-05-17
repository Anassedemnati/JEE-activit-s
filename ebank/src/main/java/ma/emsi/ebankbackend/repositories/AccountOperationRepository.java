package ma.emsi.ebankbackend.repositories;

import ma.emsi.ebankbackend.entities.AccountOperation;
import ma.emsi.ebankbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
