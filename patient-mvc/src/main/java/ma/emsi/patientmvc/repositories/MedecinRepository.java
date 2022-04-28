package ma.emsi.patientmvc.repositories;

import ma.emsi.patientmvc.entites.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Page<Medecin> findMedecinByNomContains(String nom, Pageable pageable);

}
