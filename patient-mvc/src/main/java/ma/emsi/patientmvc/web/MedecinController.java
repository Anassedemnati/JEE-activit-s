package ma.emsi.patientmvc.web;


import ma.emsi.patientmvc.entites.Medecin;
import ma.emsi.patientmvc.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedecinController {
    @Autowired
    MedecinRepository medecinRepository;

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


}
