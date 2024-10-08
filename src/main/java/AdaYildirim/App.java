package AdaYildirim;

import AdaYildirim.model.*;
//import AdaYildirim.model.Marka;
//import AdaYildirim.model.Parca;
import AdaYildirim.service.*;
import AdaYildirim.service.BarkodService;
//import AdaYildirim.service.ParcaKaydetService;
import AdaYildirim.service.VeritabaniService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("AdaYildirm Uygulaması Başlıyor...");

        Marka toyota = new Marka(1L, "Toyota");
        Marka bmw = new Marka(2L, "BMW");

        List<Parca> parcalar = new ArrayList<>();
        parcalar.add(new Parca(1L, "Sağ Ön Far", toyota, "Far", 250.00, "1234567890", 5));
        parcalar.add(new Parca(2L, "Sol Ön Far", toyota, "Far", 260.00, "1234567891", 3));
        parcalar.add(new Parca(3L, "Ön Tampon", toyota, "Tampon", 600.00, "1234567892", 2));
        //parcalar.add(new Parca(4L, "Sağ Arka Kapı", bmw, "Kapı", 800.00, "1234567893", 1));
        //parcalar.add(new Parca(5L, "Arka Sol Stop", bmw, "Stop", 400.00, "1234567894", 4));

        // Parçaları dosyalara kaydet
        try {
            ParcaKaydetService.kaydet(parcalar);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Diğer işlemler buraya eklenebilir (veritabanına ekleme, listeleme vb.)
    }
}