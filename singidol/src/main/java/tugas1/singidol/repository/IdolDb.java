package tugas1.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.singidol.model.IdolModel;

@Repository
public interface IdolDb extends JpaRepository<IdolModel, String> {
}
