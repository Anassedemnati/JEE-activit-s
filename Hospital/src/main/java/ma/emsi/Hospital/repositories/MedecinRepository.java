package ma.emsi.Hospital.repositories;

import ma.emsi.Hospital.entities.Medecin;
import ma.emsi.Hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin findByNom(String name);
}
