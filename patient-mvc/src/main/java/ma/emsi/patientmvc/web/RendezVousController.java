package ma.emsi.patientmvc.web;


import lombok.AllArgsConstructor;
import ma.emsi.patientmvc.entites.Medecin;
import ma.emsi.patientmvc.entites.Patient;
import ma.emsi.patientmvc.entites.RendezVous;
import ma.emsi.patientmvc.entites.StatusRDV;
import ma.emsi.patientmvc.repositories.MedecinRepository;
import ma.emsi.patientmvc.repositories.PatientRepository;
import ma.emsi.patientmvc.repositories.RendezVousRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.EnumSet;
import java.util.List;

@Controller
@AllArgsConstructor
public class RendezVousController {

    static EnumSet<StatusRDV> designationList = null;
    static {
        designationList = EnumSet.allOf(StatusRDV.class);
    }
    private RendezVousRepository rendezVousRepository;
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;

    @GetMapping(path = "/user/rendezvous/index")
    public String rendezvous(ModelMap model,
                              @RequestParam(name = "page", defaultValue = "0")
                                      int page,
                              @RequestParam(name = "size", defaultValue = "5")
                                      int size,
                              @RequestParam(name = "keyword", defaultValue = "")
                                      String keyword) {

        Page<RendezVous> pageRDV = rendezVousRepository.findByIdContains(keyword, PageRequest.of(page, size));
        model.addAttribute("rendezVousList", pageRDV.getContent());
        model.addAttribute("pages", new int[pageRDV.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "rendezvous";
    }
    @GetMapping("/admin/rendezvous/deleteRendezVous")
    public String deleteRendezVous(String id) {
        RendezVous rendezVous = rendezVousRepository.getById(id);
        rendezVousRepository.delete(rendezVous);
        return "redirect:/user/rendezvous/index";

    }
    @GetMapping("/admin/formRendezVous")
    public String formRendezVous(Model model) {
        List<Patient> patientList = patientRepository.findAll();
        List<Medecin> medecinList = medecinRepository.findAll();
        model.addAttribute("rendezvous", new RendezVous());
        model.addAttribute("designationList",designationList);
        model.addAttribute("patientList",patientList);
        model.addAttribute("medecinList",medecinList);
        return "formRendezVous";

    }
    @PostMapping(path = "/admin/saveRDV")
    public String saveRDV(Model model,
                       @ModelAttribute("formRendezVous") RendezVous rendezVous,
                       BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "formRendezVous";
        rendezVousRepository.save(rendezVous);
        return "redirect:/user/rendezvous/index";

    }
}
