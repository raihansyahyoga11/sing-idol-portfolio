package tugas1.singidol.service;

import tugas1.singidol.model.TipeModel;

import java.util.List;

public interface TipeService {

    List<TipeModel> getListTipe();

    TipeModel getTipeById(Long id);
}
