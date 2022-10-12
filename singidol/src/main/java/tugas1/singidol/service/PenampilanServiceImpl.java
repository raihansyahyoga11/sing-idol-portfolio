package tugas1.singidol.service;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugas1.singidol.model.KonserModel;
import tugas1.singidol.model.PenampilanModel;
import tugas1.singidol.repository.PenampilanDb;

import java.util.List;

@Service
@Transactional
public class PenampilanServiceImpl implements PenampilanService {
    @Autowired
    PenampilanDb penampilanDb;


    @Override
    public void kosongkanPenampilanKonser(KonserModel konserModel) {
        List<PenampilanModel> listPenampilan = penampilanDb.findAll();

        for (PenampilanModel penampilanKonser: listPenampilan) {
            if (penampilanKonser.getKonser().equals(konserModel) ) {
                penampilanDb.delete(penampilanKonser);
            }
        }
    }
}
