package tugas1.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.singidol.model.KonserModel;

@Repository
public interface PenampilanDb extends JpaRepository<KonserModel, String> {
}
