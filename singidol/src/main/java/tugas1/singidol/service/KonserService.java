package tugas1.singidol.service;

import tugas1.singidol.model.KonserModel;
import tugas1.singidol.model.PenampilanModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface KonserService {
    List<KonserModel> getListKonser();

    void addKonser(KonserModel konser);

    KonserModel getKonserById(Long id);

    KonserModel updateKonser(KonserModel konser);

    List<KonserModel> filterKonser(Float minPendapatan, Long idIdol);

    List<ArrayList> konserDiBonus(String namaTipe);

}
