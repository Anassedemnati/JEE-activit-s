package ma.emsi.patientmvc.web;


import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.AllArgsConstructor;
import ma.emsi.patientmvc.entites.Patient;
import ma.emsi.patientmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller

public class PatientController {
    @Autowired
    PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String patients(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "10") int size,
                           @RequestParam(name = "keyword",defaultValue ="") String keyword) {
        Page<Patient> pagePatients =patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatients.getContent());//recupere le contenu de la page en liste
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);

        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
    @GetMapping("/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @PostMapping(path = "/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/edit")
    public String editPatient(Model model,Long id,String keyword,int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient==null)throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }


    //api rest
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){

        return patientRepository.findAll();
    }



}
