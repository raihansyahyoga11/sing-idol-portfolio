package tugas1.singidol.service;

import tugas1.singidol.model.KonserModel;

import java.time.LocalDateTime;
import java.util.List;
public interface KonserService {
    List<KonserModel> getListKonser();

    void addKonser(KonserModel konser);

    KonserModel getKonserById(Long id);
}
