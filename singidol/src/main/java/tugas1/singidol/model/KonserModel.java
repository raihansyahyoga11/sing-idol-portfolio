package tugas1.singidol.model;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "konser")
public class KonserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_konser", nullable = false)
    private String namaKonser;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime waktu;


    @NotNull
    @Column(name = "total_pendapatan", nullable = false)
    private Long totalPendapatan = Long.valueOf(0);

    @NotNull
    @Size(max = 255)
    @Column(name = "tempat", nullable = false)
    private String tempat;

    @OneToMany(mappedBy = "konser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenampilanModel> penampilanKonser;

    @OneToMany(mappedBy = "konser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TiketModel> listTiket;

//    public String getNamaKonser() {
//        return namaKonser;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public LocalDateTime getWaktu() {
//        return waktu;
//    }
//
//    public Long getTotalPendapatan() {
//        return totalPendapatan;
//    }
//
//    public String getTempat() {
//        return tempat;
//    }
//
//    public List<PenampilanModel> getPenampilan() {
//        return penampilan;
//    }
//
//    public List<TiketModel> getListTiket() {
//        return listTiket;
//    }
}
