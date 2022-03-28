package ma.emsi.patientmvc;

import ma.emsi.patientmvc.entites.Patient;
import ma.emsi.patientmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Anasse",new Date(),false,12));
            patientRepository.save(new Patient(null,"mehdi",new Date(),false,24));
            patientRepository.save(new Patient(null,"Med",new Date(),false,12));
            patientRepository.save(new Patient(null,"Yahya",new Date(),true,34));
            patientRepository.findAll().forEach(patient -> {
                System.out.println("******************************");
                System.out.println(patient.getNom());
                System.out.println(patient.getDateNaissance());
                System.out.println(patient.getScore());



            });
        };
    }
}
