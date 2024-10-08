package AdaYildirim;

/*
import AdaYildirim.model.*;
import AdaYildirim.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("AdaYildirm Uygulaması Başlıyor...");

        // Modelleri oluştur
        Model corolla = new Model(1L, "Corolla");
        Model camry = new Model(2L, "Camry");
        Model x5 = new Model(3L, "X5");
        Model cClass = new Model(4L, "C-Class");

        // Markalar ve modeller
        List<Model> toyotaModelleri = new ArrayList<>();
        toyotaModelleri.add(corolla);
        toyotaModelleri.add(camry);

        List<Model> bmwModelleri = new ArrayList<>();
        bmwModelleri.add(x5);

        List<Model> mercedesModelleri = new ArrayList<>();
        mercedesModelleri.add(cClass);

        Marka toyota = new Marka(1L, "Toyota", toyotaModelleri);
        Marka bmw = new Marka(2L, "BMW", bmwModelleri);
        Marka mercedes = new Marka(3L, "Mercedes", mercedesModelleri);

        List<Parca> parcalar = new ArrayList<>();

        parcalar.add(new Parca(6L, "Sağ Ön Far", mercedes, "Far", 250.00, "1234567890", 1));
        parcalar.add(new Parca(1L, "Sağ Ön Far", toyota, "Far", 250.00, "1234567890", 5));
        parcalar.add(new Parca(2L, "Sol Ön Far", toyota, "Far", 260.00, "1234567891", 3));
        parcalar.add(new Parca(3L, "Ön Tampon", toyota, "Tampon", 600.00, "1234567892", 2));
        parcalar.add(new Parca(4L, "Sağ Arka Kapı", bmw, "Kapı", 800.00, "1234567893", 1));
        parcalar.add(new Parca(5L, "Arka Sol Stop", bmw, "Stop", 400.00, "1234567894", 4));

        // Parçaları dosyalara kaydet
        try {
            ParcaKaydetService.kaydet(parcalar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Marka> markalar = new ArrayList<>();
        markalar.add(toyota);
        markalar.add(bmw);
        markalar.add(mercedes);

        // Markaları ve modelleri dosyaya kaydet
        try {
            MarkaKaydetService.kaydetMarkaVeModeller(markalar);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Modeller dosyasını oluştur
            ModelDosyasiService.modelDosyasiOlustur();

            // Dosyaya parça bilgisi ekle
            ModelDosyasiService.parcayiDosyayaEkle("Sağ Ön Far - Mercedes - Barkod: 1234567890");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Diğer işlemler buraya eklenebilir (veritabanına ekleme, listeleme vb.)
    }
}*/
import AdaYildirim.model.*;
import AdaYildirim.service.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Toyota modellerini oluştur ve listeye ekle
        List<Model> toyotaModelleri = new ArrayList<>();
        toyotaModelleri.add(new Model(1L, "Auris"));
        toyotaModelleri.add(new Model(2L, "Avensis"));
        toyotaModelleri.add(new Model(3L, "Camry"));
        toyotaModelleri.add(new Model(4L, "Carina"));
        toyotaModelleri.add(new Model(5L, "Celica"));
        toyotaModelleri.add(new Model(6L, "Corolla"));
        toyotaModelleri.add(new Model(7L, "Corona"));
        toyotaModelleri.add(new Model(8L, "Cressida"));
        toyotaModelleri.add(new Model(9L, "Estima"));
        toyotaModelleri.add(new Model(10L, "GT86"));
        toyotaModelleri.add(new Model(11L, "MR2"));
        toyotaModelleri.add(new Model(12L, "Picnic"));
        toyotaModelleri.add(new Model(13L, "Previa"));
        toyotaModelleri.add(new Model(14L, "Prius"));
        toyotaModelleri.add(new Model(15L, "Starlet"));
        toyotaModelleri.add(new Model(16L, "Supra"));
        toyotaModelleri.add(new Model(17L, "Tercel"));
        toyotaModelleri.add(new Model(18L, "Urban Cruiser"));
        toyotaModelleri.add(new Model(19L, "Verso"));
        toyotaModelleri.add(new Model(20L, "Yaris"));
        toyotaModelleri.add(new Model(21L, "C-HR"));
        toyotaModelleri.add(new Model(22L, "Corolla Cross"));
        toyotaModelleri.add(new Model(23L, "FJ Cruiser"));
        toyotaModelleri.add(new Model(24L, "Hilux"));
        toyotaModelleri.add(new Model(25L, "Land Cruiser"));
        toyotaModelleri.add(new Model(26L, "RAV4"));
        toyotaModelleri.add(new Model(27L, "Yaris Cross"));
        toyotaModelleri.add(new Model(28L, "4Runner"));
        toyotaModelleri.add(new Model(29L, "Sequoia"));
        toyotaModelleri.add(new Model(30L, "Tacoma"));
        toyotaModelleri.add(new Model(31L, "Tundra"));

        // Toyota markasını oluştur
        Marka toyota = new Marka(1L, "Toyota", toyotaModelleri);

        // Toyota markasının modellerini for döngüsü ile kaydet
        try {
            File anaKlasor = new File("markalar/Toyota");

            // Eğer Toyota klasörü yoksa oluştur
            if (!anaKlasor.exists()) {
                anaKlasor.mkdirs();
            }

            // For döngüsü ile tüm Toyota modellerini kaydet
            for (Model model : toyota.getModeller()) {
                File modelKlasoru = new File(anaKlasor, model.getModelIsmi());
                if (!modelKlasoru.exists()) {
                    modelKlasoru.mkdirs();  // Eğer model klasörü yoksa oluştur
                    System.out.println(model.getModelIsmi() + " modeli için klasör oluşturuldu.");
                } else {
                    System.out.println(model.getModelIsmi() + " modeli zaten mevcut.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
