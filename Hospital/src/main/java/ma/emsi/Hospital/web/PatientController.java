package ma.emsi.Hospital.web;


import ma.emsi.Hospital.entities.Patient;
import ma.emsi.Hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/patients")
    List<Patient> patientList(){
        return patientRepository.findAll();
    }
    //TODO finish all and point
}
