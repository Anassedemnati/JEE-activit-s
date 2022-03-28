package ma.emsi.patientmvc.repositories;

import ma.emsi.patientmvc.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
