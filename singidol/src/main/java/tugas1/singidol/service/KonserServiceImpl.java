package tugas1.singidol.service;

import tugas1.singidol.model.KonserModel;
import tugas1.singidol.model.PenampilanModel;
import tugas1.singidol.repository.KonserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugas1.singidol.repository.KonserDb;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KonserServiceImpl implements KonserService{

    @Autowired
    KonserDb konserDb;

    @Override
    public List<KonserModel> getListKonser() {
        return konserDb.findAll();
    }

    @Override
    public void addKonser(KonserModel konser) {
        konserDb.save(konser);
    }

    @Override
    public KonserModel getKonserById(Long id) {
        Optional<KonserModel> konser = konserDb.findById(id);
        if (konser.isPresent()) {
            return konser.get();
        } else return null;
    }

    @Override
    public KonserModel updateKonser(KonserModel konser) {
        konserDb.save(konser);
        return konser;
    }

    @Override
    public List<KonserModel> filterKonser(Float pendapatanMinimum, Long idIdol) {

        List<KonserModel> tampung = new ArrayList<>();
        List<KonserModel> listKonser = konserDb.findAll();

        for (KonserModel konser: listKonser) {
            if (konser.getTotalPendapatan() >= pendapatanMinimum) {
                for (PenampilanModel penampilanModel: konser.getPenampilanKonser()) {
                    if (penampilanModel.getIdol().getId() == idIdol) {
                        tampung.add(konser);
                        break;
                    }
                }
            }
        }
        return tampung;
    }
    public List<ArrayList> konserDiBonus(String namaTipe) {
        List<ArrayList> konserTertinggiSesuaiTipe = konserDb.bonusKonser(namaTipe);
        return konserTertinggiSesuaiTipe;
    }

}
