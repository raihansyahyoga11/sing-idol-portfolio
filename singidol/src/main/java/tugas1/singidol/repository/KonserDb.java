package tugas1.singidol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tugas1.singidol.model.KonserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface KonserDb extends JpaRepository<KonserModel, String> {
    Optional<KonserModel> findById(Long id);

    @Query(value = "SELECT k.*, COUNT(tp.nama=?1) as jumlah from konser k join tiket t on k.id = t.id_konser join tipe tp on tp.id = t.id_tipe and tp.nama=?1 GROUP by k.id HAVING COUNT(tp.nama=?1)=(jumlah) ORDER BY jumlah DESC, k.nama_konser ASC", nativeQuery = true)
    List<ArrayList> bonusKonser(String tipe);
}
