package tugas1.singidol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugas1.singidol.model.IdolModel;
import tugas1.singidol.repository.IdolDb;
import tugas1.singidol.repository.KonserDb;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class IdolServiceImpl implements IdolService {

    @Autowired
    IdolDb idolDb;

    @Override
    public List<IdolModel> getListIdol() {
        return idolDb.findAll();
    }

    @Override
    public void addIdol(IdolModel idol) {
        idolDb.save(idol);
    }

    @Override
    public IdolModel findIdolById(Long id) {
        Optional<IdolModel> idolFound = idolDb.findById(id);
        if (idolFound.isEmpty()) {
            return null;
        }
        else {
            return idolFound.get();
        }
    }
}
