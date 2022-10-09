package tugas1.singidol.controller;

import tugas1.singidol.model.PenampilanModel;
import tugas1.singidol.model.KonserModel;
import tugas1.singidol.model.IdolModel;
import tugas1.singidol.model.TiketModel;
import tugas1.singidol.model.TipeModel;

import tugas1.singidol.service.KonserService;
import tugas1.singidol.service.IdolService;
import tugas1.singidol.service.PenampilanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

@Controller
public class KonserController {

    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;

    @Qualifier("idolServiceImpl")
    @Autowired
    private IdolService idolService;

    @Qualifier("penampilanServiceImpl")
    @Autowired
    private PenampilanService penampilanService;

    @GetMapping("/konser/viewall")
    public String listKonser(Model model) {
        List<KonserModel> listKonser = konserService.getListKonser();
        model.addAttribute("listKonser", listKonser);
        return "viewall-konser";
    }

    @GetMapping("/konser/add")
    public String addKonserFormPage(Model model){
        KonserModel konser = new KonserModel();
        List<IdolModel> listIdol = idolService.getListIdol();
        List<PenampilanModel> listPenampilanNew = new ArrayList<>();

        konser.setPenampilanKonser(listPenampilanNew);
        konser.getPenampilanKonser().add(new PenampilanModel());

//        List<PengajarModel> listPengajarNew = new ArrayList<>();
//        course.setListPengajar(listPengajarNew);
//        course.getListPengajar().add(new PengajarModel());

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);
        return "form-add-konser";
    }

    // no 4
    @PostMapping(value = "/konser/add", params = {"save"})
    public String addKonserSubmit(@ModelAttribute KonserModel konser, Model model){
//        if (course.getListPengajar() == null) {
//            course.setListPengajar(new ArrayList<>());
//        }
//        System.out.println(konser.toString());
//        for (PenampilanModel penampilan : konser.getPenampilan()) {
//            System.out.println(penampilan.getIdIdol().getNamaIdol());
//        }
        if (konser.getPenampilanKonser() == null) {

            konser.setPenampilanKonser(new ArrayList<>());
            System.out.println("if");
        }
        else {
            System.out.println("else");
            for (int i = 0; i < konser.getPenampilanKonser().size(); i++) {
                konser.getPenampilanKonser().get(i).setKonser(konser);
                System.out.println(konser.getPenampilanKonser().get(i).getKonser().getId());
            }

        }
        konserService.addKonser(konser);
//        penampilanService.addPenampilan(konser.getPenampilan())
//        System.out.println(konser.getCode());
        model.addAttribute("namaKonser", konser.getNamaKonser());
//        model.addAttribute("success", "Sorry, pengajar tidak berhasil didapatkan");
        return "add-konser";
    }

    @PostMapping(value = "/konser/add", params = {"addRow"})
    private String addRowPenampilanMultiple(
            @ModelAttribute KonserModel konser,
            Model model
    ) {
        if (konser.getPenampilanKonser() == null || konser.getPenampilanKonser().size() == 0) {
            konser.setPenampilanKonser(new ArrayList<>());
        }
        konser.getPenampilanKonser().add(new PenampilanModel());
        List<IdolModel> listIdol = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "form-add-konser";
    }

    @PostMapping(value = "konser/add", params = {"deleteRow"})
    public String deleteRowPenampilanMultiple(
            @ModelAttribute KonserModel konser,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final  Integer rowId = Integer.valueOf(row);
        konser.getPenampilanKonser().remove(rowId.intValue());

//        List<IdolModel> listIdol = idolService.getListIdol();
        List<IdolModel> listIdol = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "form-add-konser";

    }

    @GetMapping("/konser/view")
    public String viewDetailKonserPage(@RequestParam(value = "id") String id, Model model) {
        Long idParsed = Long.parseLong(id);
        KonserModel konser = konserService.getKonserById(idParsed);
        List<PenampilanModel> listPenampilan = konser.getPenampilanKonser();
        model.addAttribute("listPenampilan", listPenampilan);
        model.addAttribute("konser", konser);
        return "view-konser";
    }
}
