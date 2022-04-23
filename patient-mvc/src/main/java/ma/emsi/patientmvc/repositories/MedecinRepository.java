package ma.emsi.patientmvc.repositories;

import ma.emsi.patientmvc.entites.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    Medecin findMedecinByNom(String nom);

}
