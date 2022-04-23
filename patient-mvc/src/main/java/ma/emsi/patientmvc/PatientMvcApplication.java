package ma.emsi.patientmvc;

import ma.emsi.patientmvc.entites.Patient;
import ma.emsi.patientmvc.repositories.PatientRepository;
import ma.emsi.patientmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Anasse",new Date(),false,112));
            patientRepository.save(new Patient(null,"mehdi",new Date(),false,124));
            patientRepository.save(new Patient(null,"Medahe",new Date(),false,112));
            patientRepository.save(new Patient(null,"Yahya",new Date(),true,134));
           /* patientRepository.findAll().forEach(patient -> {
                System.out.println("******************************");
                System.out.println(patient.getNom());
                System.out.println(patient.getDateNaissance());
                System.out.println(patient.getScore());
            });*/
        };
    }
   // @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            //ajouter des user
            securityService.saveNewUser("Anasse","1234","1234");
            securityService.saveNewUser("Boutaina","1234","1234");
            securityService.saveNewUser("Nabile","1234","1234");
            //ajouter des role
            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");
            //ajouter des role au utulistaeur
            securityService.addRoleToUser("Anasse","ADMIN");
            securityService.addRoleToUser("Boutaina","USER");
            securityService.addRoleToUser("Nabile","USER");


        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
