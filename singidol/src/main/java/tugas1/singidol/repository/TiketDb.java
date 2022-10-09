package tugas1.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tugas1.singidol.model.KonserModel;
import tugas1.singidol.model.PenampilanModel;
import tugas1.singidol.model.TiketModel;

import java.math.BigInteger;
import java.util.Optional;

public interface TiketDb extends JpaRepository<TiketModel, Long> {

    Optional<TiketModel> findById(Long id);
}
