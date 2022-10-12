package tugas1.singidol.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tugas1.singidol.model.TiketModel;
import tugas1.singidol.model.KonserModel;
import tugas1.singidol.repository.KonserDb;
import tugas1.singidol.repository.PenampilanDb;
import tugas1.singidol.repository.TiketDb;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@Transactional
public class TiketServiceImpl implements TiketService{

    @Autowired
    TiketDb tiketDb;

    @Autowired
    KonserDb konserDb;

    @Override
    public List<TiketModel> getAllTiket() {
        return tiketDb.findAll();
    }

    @Override
    public TiketModel getTiketById(Long id) {
        Optional<TiketModel> tiketFound = tiketDb.findById(id);
        if (tiketFound.isEmpty()) {
            return null;
        }
        else {
            return tiketFound.get();
        }
    }

    @Override
    public String getRandomizedChar() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 1; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

//    @Override
//    public String

    @Override
    public void addTiket(TiketModel tiket) {

        String nomorTiket = "";
        nomorTiket += tiket.getNamaLengkap().substring(0,3).toUpperCase();

        LocalDate localDate = LocalDate.now();
        int monthToday = localDate.getMonthValue();
        int dateToday = localDate.getDayOfMonth();

        String hariBulanLahir = String.format("%2s", tiket.getTanggalLahir().getDayOfMonth()).replace(' ', '0')
                +
                String.format("%2s", tiket.getTanggalLahir().getMonthValue()).replace(' ', '0');
        String hariBulanIni = String.format("%2s", dateToday).replace(' ', '0') + String.format("%2s", monthToday).replace(' ', '0');

        int hariIniInt = Integer.parseInt(hariBulanIni);
        int hariBulanLahirInt =  Integer.parseInt(hariBulanLahir);
        int penjumlahanDDMM = hariIniInt+hariBulanLahirInt;
        String convertPenjumlahan = String.valueOf(penjumlahanDDMM);
        nomorTiket += convertPenjumlahan;

        String hurufDepanKonser = "";
        List<String> alphabet = List.of("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");

        for (int i = 0; i < alphabet.size(); i++) {
            if(tiket.getKonser().getNamaKonser().substring(0,1).equalsIgnoreCase(String.valueOf(alphabet.get(i)))) {
                hurufDepanKonser = String.format("%2s",
                        String.valueOf(i+1)).replace(' ', '0');
            }
        }

        nomorTiket += hurufDepanKonser;

        String tipeTiketSimpan = "";
        if(tiket.getTipe().getNama().equals("vip")) {
            tipeTiketSimpan =  "VIP";
        } else if (tiket.getTipe().getNama().equals("platinum")) {
            tipeTiketSimpan = "PLT";
        }  else if (tiket.getTipe().getNama().equals("gold")) {
            tipeTiketSimpan = "GLD";
        } else {
            tipeTiketSimpan = "SLV";
        }


        nomorTiket += tipeTiketSimpan;


        nomorTiket += getRandomizedChar();

        tiket.setTanggalPembelian(LocalDateTime.now());
        tiket.setNomorTiket(nomorTiket);

        KonserModel konser = konserDb.findById(tiket.getKonser().getId()).get();

        konser.setTotalPendapatan(konser.getTotalPendapatan() + (tiket.getTipe().getHarga()));

        konserDb.save(konser);
        tiketDb.save(tiket);
    }

    public void hapusTiket(TiketModel tiketHarga) {
        KonserModel konser = konserDb.findById(tiketHarga.getKonser().getId()).get();

        konser.setTotalPendapatan(konser.getTotalPendapatan() - (tiketHarga.getTipe().getHarga()));
        konserDb.save(konser);
        tiketDb.delete(tiketHarga);
    }


}
