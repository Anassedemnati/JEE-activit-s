package ma.emsi.Hospital.repositories;

import ma.emsi.Hospital.entities.Medecin;
import ma.emsi.Hospital.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous,String> {
    
}
