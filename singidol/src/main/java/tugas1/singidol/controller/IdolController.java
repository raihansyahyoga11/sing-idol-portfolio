package tugas1.singidol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import tugas1.singidol.model.IdolModel;

import tugas1.singidol.service.IdolService;
import tugas1.singidol.service.KonserService;

import java.util.List;


@Controller
public class IdolController {

    @Qualifier("idolServiceImpl")
    @Autowired
    private IdolService idolService;
    @GetMapping("/idol/viewall")
    private String listIdol(Model model) {
        List<IdolModel> list = idolService.getListIdol();

        model.addAttribute("listIdol", list);
        model.addAttribute("hasIdol", list.size()>0);
        return "viewall-idol";
    }

    @GetMapping("/idol/tambah")
    public String tambahIdolFormPage(Model model) {
        model.addAttribute("idol", new IdolModel());
        return "form-add-idol";
    }

    @PostMapping("/idol/tambah")
    public String submitTambahIdol(@ModelAttribute IdolModel idol, Model model) {
        if (idol.getTanggalDebut()== null) {
            model.addAttribute("failed", "Anda harus mengisi field dengan lengkap");
            return "error/failed";
        }
        idolService.addIdol(idol);
        model.addAttribute("nama", idol.getNamaIdol());
        return "add-idol";
    }

    @GetMapping("/idol/{id}")
    public String lihatDetailIdol(
            @PathVariable Long id,
            Model model
    ){
        IdolModel idol = idolService.findIdolById(id);
        if(idol == null){
            model.addAttribute("failed", "Idol tersebut tidak ada");
            return "error/failed";
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        model.addAttribute("idol",idol);
        return "view-idol";
    }
}
