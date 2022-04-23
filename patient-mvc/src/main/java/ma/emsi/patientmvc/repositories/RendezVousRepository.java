package ma.emsi.patientmvc.repositories;

import ma.emsi.patientmvc.entites.RendezVous;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous,String> {
    RendezVous findByIdContains(String id);
}
