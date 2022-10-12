package tugas1.singidol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import tugas1.singidol.model.PenampilanModel;
import tugas1.singidol.model.KonserModel;
import tugas1.singidol.model.IdolModel;
import tugas1.singidol.model.TiketModel;
import tugas1.singidol.model.TipeModel;

import tugas1.singidol.service.KonserService;
import tugas1.singidol.service.IdolService;
import tugas1.singidol.service.PenampilanService;
import tugas1.singidol.service.TiketService;
import tugas1.singidol.service.TipeService;

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

import java.util.List;
@Controller
public class TiketController {

    @Qualifier("tiketServiceImpl")
    @Autowired
    private TiketService tiketService;

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;

    @GetMapping("/tiket/viewall")
    public String listTiket(Model model) {
        List<TiketModel> listTiket = tiketService.getAllTiket();
        model.addAttribute("listTiket", listTiket);
        model.addAttribute("hasTiket", listTiket.size()>0);
        System.out.println("masuk ke html");
        return "viewall-tiket";
    }

    @GetMapping("/tiket/{id}")
    public String detailTiket(
            @PathVariable Long id,
            Model model
    ){
        TiketModel tiket = tiketService.getTiketById(id);
        if(tiket == null){
            model.addAttribute("failed", "Tiket tersebut tidak ada");
            return "error/failed";
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        model.addAttribute("tiket",tiket);
        return "view-tiket";
    }

    @GetMapping("/tiket/pesan")
    public String pesanTiketFormPage(Model model){
        TiketModel tiket = new TiketModel();
        List<TipeModel> listTipe = tipeService.getListTipe();
        List<KonserModel> listKonser = konserService.getListKonser();
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listKonser", listKonser);
        model.addAttribute("tiket", tiket);
        return "form-add-tiket";
    }

    @PostMapping("/tiket/pesan")
    public String pesanTiketSubmit(@ModelAttribute TiketModel tiket, Model model) {
        if (tiket.getTanggalLahir()== null) {
            model.addAttribute("failed", "Anda harus mengisi field dengan lengkap");
            return "error/failed";
        }
        tiket.setKonser(konserService.getKonserById(tiket.getKonser().getId().longValue()));
        tiket.setTipe(tipeService.getTipeById(tiket.getTipe().getId()));
        tiketService.addTiket(tiket);
        model.addAttribute("noTiket", tiket.getNomorTiket());
        model.addAttribute("namaKonser", tiket.getKonser().getNamaKonser());
        return "add-tiket";
    }

    @GetMapping("/tiket/hapus/{id}")
    public String hapusTiket(@PathVariable Long id ,Model model){
        TiketModel tiket = tiketService.getTiketById(id);
        if(tiket == null){
            model.addAttribute("failed", "Tiket tersebut tidak ada");
            return "error/failed";
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        tiketService.hapusTiket(tiket);
        model.addAttribute("nomor", tiket.getNomorTiket());
        model.addAttribute("namaKonser", tiket.getKonser().getNamaKonser());
        return "hapus-tiket";
    }
}
