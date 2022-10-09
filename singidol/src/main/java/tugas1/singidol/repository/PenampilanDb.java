package tugas1.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugas1.singidol.model.KonserModel;
import tugas1.singidol.model.PenampilanModel;

import java.util.List;

@Repository
public interface PenampilanDb extends JpaRepository<PenampilanModel, String> {
}
