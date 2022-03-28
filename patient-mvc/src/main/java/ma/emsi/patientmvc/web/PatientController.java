package ma.emsi.patientmvc.web;


import ma.emsi.patientmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PatientController {
    @Autowired
    PatientRepository patientRepository;
}
