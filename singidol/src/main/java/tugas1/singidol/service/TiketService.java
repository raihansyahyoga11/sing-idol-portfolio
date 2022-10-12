package tugas1.singidol.service;

import tugas1.singidol.model.TiketModel;

import java.util.List;

public interface TiketService {

    List<TiketModel> getAllTiket();

    TiketModel getTiketById(Long id);

    String getRandomizedChar();

    void pesanTiket(TiketModel tiket);

    void hapusTiket(TiketModel tiketModel);
}
