package ma.emsi.ebankbackend.repositories;

import ma.emsi.ebankbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
