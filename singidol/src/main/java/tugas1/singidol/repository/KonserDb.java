package tugas1.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.singidol.model.KonserModel;

import java.util.Optional;

@Repository
public interface KonserDb extends JpaRepository<KonserModel, String> {
    Optional<KonserModel> findById(Long id);
}
