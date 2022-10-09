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

//    @Override
//    public void addPenampilan(List<PenampilanModel> penampilanModel) {
//        penampilanDb.save(penampilanModel);
//    };

    @Override
    public void emptyPenampilanKonser(KonserModel konserModel) {
        List<PenampilanModel> listPenampilanKonser = penampilanDb.findAll();
        for (PenampilanModel penampilanKonser: listPenampilanKonser) {
            if (penampilanKonser.getKonser() == konserModel) {
                penampilanDb.delete(penampilanKonser);
            }
        }
    }
}
