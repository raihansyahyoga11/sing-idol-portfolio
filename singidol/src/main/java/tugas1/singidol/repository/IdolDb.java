package tugas1.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.singidol.model.IdolModel;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface IdolDb extends JpaRepository<IdolModel, Long> {

    public Optional<IdolModel> findById(Long id);
}
