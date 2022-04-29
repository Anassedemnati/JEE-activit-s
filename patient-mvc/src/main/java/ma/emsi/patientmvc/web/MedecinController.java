package ma.emsi.patientmvc.web;


import ma.emsi.patientmvc.entites.Medecin;
import ma.emsi.patientmvc.entites.Patient;
import ma.emsi.patientmvc.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MedecinController {
    @Autowired
    MedecinRepository medecinRepository;

    static List<String> designationList = null;
    static {
        designationList = new ArrayList<>();
        designationList.add("Medecin géneral");
        designationList.add("Medecin ophtamologue");
        designationList.add("Medecin génichologue");
        designationList.add("Medecin cardiologue");
    }


    @GetMapping(path = "/user/medecin/index")
    public String medecin(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "10") int size,
                          @RequestParam(name = "keyword",defaultValue ="") String keyword){

        Page<Medecin> pageMedecin = medecinRepository.findMedecinByNomContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listMedecin",pageMedecin.getContent());
        model.addAttribute("page",new int[pageMedecin.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "medecins";
    }
    @GetMapping("/admin/medecin/formMedecin")
    public String formMedecin(Model model){
        model.addAttribute("medecin",new Medecin());
        model.addAttribute("designationList",designationList);
        return "formMedecin";
    }
    @PostMapping(path = "/admin/medecin/save")
    public String save(Model model, @Valid Medecin medecin, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formMedecin";
        medecinRepository.save(medecin);
        return "redirect:/user/medecin/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/medecin/editMedecin")
    public String editMedecin(Model model, Long id) {
        Medecin medecin =medecinRepository.findById(id).orElse(null) ;
        if(medecin==null) throw  new RuntimeException("Medecin introuvable");
        model.addAttribute("medecin",medecin);
        model.addAttribute("designationList",designationList);

        return "editMedecin";

    }


}
