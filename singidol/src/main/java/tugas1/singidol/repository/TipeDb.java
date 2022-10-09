package tugas1.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tugas1.singidol.model.PenampilanModel;
import tugas1.singidol.model.TipeModel;

import java.math.BigInteger;
import java.util.Optional;

public interface TipeDb extends JpaRepository<TipeModel, Long> {

    public Optional<TipeModel> findById(Long id);
}
