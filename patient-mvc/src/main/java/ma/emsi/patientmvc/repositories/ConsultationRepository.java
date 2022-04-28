package ma.emsi.patientmvc.repositories;

import ma.emsi.patientmvc.entites.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

}
