package ma.emsi.Hospital;

import ma.emsi.Hospital.entities.*;
import ma.emsi.Hospital.repositories.ConsultationRepository;
import ma.emsi.Hospital.repositories.MedecinRepository;
import ma.emsi.Hospital.repositories.PatientRepository;
import ma.emsi.Hospital.repositories.RendezVousRepository;
import ma.emsi.Hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean // la methode sexecute au demarage
	//obj retourne par la mathode sera un composant spring
	CommandLineRunner start(IHospitalService hospitalService,MedecinRepository medecinRepository, PatientRepository patientRepository,RendezVousRepository rendezVousRepository,ConsultationRepository consultationRepository){

		return args -> {
			Stream.of("Liam","Olivia", "Noah","Emma", "Oliver","Ava" ,"Elijah","Charlotte")
					.forEach(name->{
						Medecin medecin = new Medecin();

						medecin.setNom(name);
						medecin.setEmail(name+"@exp.com");
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						hospitalService.saveMedecin(medecin);
					});
			Stream.of("Mohamed","Mehdi","Fati")
					.forEach(name->{
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						hospitalService.savePatient(patient);
					});
			Patient patient = patientRepository.findById(1L).orElse(null);
			Medecin medecin = medecinRepository.findById(1L).orElse(null);
			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			hospitalService.saveRendezVous(rendezVous);

			RendezVous rendezVous1= rendezVousRepository.findAll().get(0);
			Consultation consultation = new Consultation();
			consultation.setDateConsultaion(rendezVous1.getDate());
			consultation.setRapport("bon ");
			consultation.setRendezVous(rendezVous1);
			consultationRepository.save(consultation);


		};
	}
}
