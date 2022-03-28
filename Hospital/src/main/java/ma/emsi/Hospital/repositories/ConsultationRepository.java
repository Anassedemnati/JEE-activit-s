package ma.emsi.Hospital.repositories;

import ma.emsi.Hospital.entities.Consultation;
import ma.emsi.Hospital.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    
}
