package tugas1.singidol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugas1.singidol.model.TipeModel;
import tugas1.singidol.repository.TipeDb;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{

    @Autowired
    TipeDb tipeDb;
    public List<TipeModel> getListTipe() {
        return tipeDb.findAll();
    }

    public TipeModel getTipeById(Long id) {
        Optional<TipeModel> opt = tipeDb.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        else {
            return opt.get();
        }
    }
}
