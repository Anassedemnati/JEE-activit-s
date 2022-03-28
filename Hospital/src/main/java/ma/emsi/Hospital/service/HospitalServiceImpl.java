package ma.emsi.Hospital.service;

import ma.emsi.Hospital.entities.Consultation;
import ma.emsi.Hospital.entities.Medecin;
import ma.emsi.Hospital.entities.Patient;
import ma.emsi.Hospital.entities.RendezVous;
import ma.emsi.Hospital.repositories.ConsultationRepository;
import ma.emsi.Hospital.repositories.MedecinRepository;
import ma.emsi.Hospital.repositories.PatientRepository;
import ma.emsi.Hospital.repositories.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.callback.ReactiveEntityCallbacks;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous)
    {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
