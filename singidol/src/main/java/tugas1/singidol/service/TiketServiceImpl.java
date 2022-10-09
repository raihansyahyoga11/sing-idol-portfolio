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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.util.Random;

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
        Optional<TiketModel> opt = tiketDb.findById(id);
        return opt.isEmpty() ? null : opt.get();
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

    @Override
    public void addTiket(TiketModel tiket) {

        String noTiket = "";
        noTiket += tiket.getNamaLengkap().substring(0,3).toUpperCase();

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int monthToday = localDate.getMonthValue();
        int dateToday = localDate.getDayOfMonth();

        String a = String.format("%2s", tiket.getTanggalLahir().getDayOfMonth()).replace(' ', '0') + String.format("%2s", tiket.getTanggalLahir().getMonthValue()).replace(' ', '0');
        String b = String.format("%2s", dateToday).replace(' ', '0') + String.format("%2s", monthToday).replace(' ', '0');

        String res = "";
        String res2 = "";

        int x = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            int y = Integer.valueOf(String.valueOf(a.charAt(i))) + Integer.valueOf(String.valueOf(b.charAt(i))) + x;

            if (y >= 10) {
                res += String.valueOf(y).charAt(1);
                x += 1;
            } else {
                res += String.valueOf(y);
                x = 0;
            }
        }

        for (int i = res.length() - 1; i >= 0; i--) {
            res2 += res.substring(i,i+1);
        }

        noTiket += res2;

        String xTemp = "";
        char[] map = new char[26];
        String abj = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < abj.length(); i++) {
            map[i] = abj.charAt(i);
        }

        for (int i = 0; i < map.length; i++) {
            if(tiket.getKonser().getNamaKonser().substring(0,1).equalsIgnoreCase(String.valueOf(map[i]))) {
                xTemp = String.format("%2s", String.valueOf(i+1)).replace(' ', '0');
            }
        }

        noTiket += xTemp;

        String tipeSimp = "";
        if(tiket.getTipe().getNama().equalsIgnoreCase("vip")) {
            tipeSimp =  "VIP";
        } else if (tiket.getTipe().getNama().equalsIgnoreCase("Platinum")) {
            tipeSimp = "PLT";
        }  else if (tiket.getTipe().getNama().equalsIgnoreCase("GOLD")) {
            tipeSimp = "GLD";
        } else {
            tipeSimp = "SLV";
        }
        noTiket += tipeSimp;
        noTiket += getRandomizedChar();

        tiket.setNomorTiket(noTiket);
        tiket.setTanggalPembelian(LocalDateTime.now());
        KonserModel konser = konserDb.findById(tiket.getKonser().getId()).get();
        konser.setTotalPendapatan(konser.getTotalPendapatan() + (tiket.getTipe().getHarga()));
        konserDb.save(konser);
        tiketDb.save(tiket);
    }

    public void hapusTiket(TiketModel tiketModel) {
        KonserModel konser = konserDb.findById(tiketModel.getKonser().getId()).get();
        konser.setTotalPendapatan(konser.getTotalPendapatan() - (tiketModel.getTipe().getHarga()));
        konserDb.save(konser);
        tiketDb.delete(tiketModel);
    }


}
