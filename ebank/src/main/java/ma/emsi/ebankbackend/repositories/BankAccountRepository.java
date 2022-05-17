package ma.emsi.ebankbackend.repositories;

import ma.emsi.ebankbackend.entities.BankAccount;
import ma.emsi.ebankbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
