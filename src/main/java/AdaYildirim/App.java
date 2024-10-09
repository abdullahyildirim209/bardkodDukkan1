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


/*
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Marka seçimi
        System.out.println("Lütfen marka adını giriniz (Örneğin: Mercedes): ");
        String marka = scanner.nextLine();

        // 2. Model seçimi
        System.out.println("Lütfen model adını giriniz (Örneğin: C Serisi): ");
        String model = scanner.nextLine();

        // 3. Parça türü seçimi
        System.out.println("Lütfen parça türünü seçiniz (Kapı, Kaput, Tampon, Far, Stop): ");
        String parcaTuru = scanner.nextLine();

        // 4. Yön seçimi
        String[] yonSecenekleri;
        if (parcaTuru.equalsIgnoreCase("Kapı") || parcaTuru.equalsIgnoreCase("Çamurluk")) {
            yonSecenekleri = new String[]{"Sağ Ön", "Sağ Arka", "Sol Ön", "Sol Arka"};
        } else if (parcaTuru.equalsIgnoreCase("Far") || parcaTuru.equalsIgnoreCase("Stop")) {
            yonSecenekleri = new String[]{"Sağ", "Sol"};
        } else if (parcaTuru.equalsIgnoreCase("Kaput") || parcaTuru.equalsIgnoreCase("Tampon")) {
            yonSecenekleri = new String[]{"Ön", "Arka"};
        } else {
            System.out.println("Geçersiz parça türü seçildi!");
            return;
        }

        System.out.println("Lütfen yön seçiniz:");
        for (int i = 0; i < yonSecenekleri.length; i++) {
            System.out.println((i + 1) + ". " + yonSecenekleri[i]);
        }

        // Kullanıcıdan sayısal bir girdi bekleniyor
        int yonSecimi = 0;
        boolean gecerliSecim = false;
        while (!gecerliSecim) {
            try {
                // Giriş satır olarak alınır ve tam sayıya dönüştürülür
                String secim = scanner.nextLine();
                yonSecimi = Integer.parseInt(secim);

                // Yön seçiminin geçerli olup olmadığını kontrol et
                if (yonSecimi < 1 || yonSecimi > yonSecenekleri.length) {
                    System.out.println("Geçersiz yön seçimi! Lütfen geçerli bir seçim yapınız.");
                } else {
                    gecerliSecim = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş! Lütfen bir sayı giriniz.");
            }
        }

        String secilenYon = yonSecenekleri[yonSecimi - 1];

        // 5. Barkod ve resim dosyası bilgileri
        System.out.println("Lütfen barkod bilgisini giriniz: ");
        String barkod = scanner.nextLine();

        System.out.println("Lütfen resim dosyasının yolunu giriniz (Örneğin: C:\\Users\\Abdullah Yusuf\\Desktop): ");
        String resimYolu = scanner.nextLine();
        File kaynakResim = new File(resimYolu);

        // 6. Konum bilgisi
        System.out.println("Lütfen konumu giriniz (Örneğin: Yazıhanenin üstündeki 3. rafta): ");
        String konum = scanner.nextLine();

        // Hedef dizin yapısı
        String hedefDizin = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/markalar/"
                + marka + "/" + model + "/" + parcaTuru + "/" + secilenYon + "/";

        // Resim ve barkod dosyası ismi
        String dosyaAdi = model.toLowerCase().replace(" ", "_") + "_" + parcaTuru.toLowerCase() + "_" + secilenYon.toLowerCase().replace(" ", "_");
        File hedefResim = new File(hedefDizin + dosyaAdi + ".jpg");
        File barkodDosyasi = new File(hedefDizin + dosyaAdi + ".txt");

        try {
            // Hedef dizini oluştur
            File hedefKlasor = new File(hedefDizin);
            if (!hedefKlasor.exists()) {
                hedefKlasor.mkdirs();  // Dizin yoksa oluştur
            }

            // 7. Resmi hedef dizine kopyala
            Files.copy(kaynakResim.toPath(), hedefResim.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Resim başarıyla kaydedildi: " + hedefResim.getPath());

            // 8. Barkod ve konum bilgisini metin dosyasına yaz
            try (FileWriter writer = new FileWriter(barkodDosyasi)) {
                writer.write("Barkod: " + barkod + "\n");
                writer.write("Konum: " + konum + "\n");
                writer.write("Yön: " + secilenYon + "\n");
                System.out.println("Barkod ve konum bilgisi başarıyla kaydedildi: " + barkodDosyasi.getPath());
            }

        } catch (IOException e) {
            System.out.println("Kayıt işlemi sırasında hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class App {

    // Barkod başlangıç değeri
    static int barkodNumarasi = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Marka seçimi
        System.out.println("Lütfen marka adını giriniz (Örneğin: Mercedes): ");
        String marka = scanner.nextLine().trim().toLowerCase();  // Büyük/Küçük harf duyarlılığı kaldırıldı

        // 2. Model seçimi
        System.out.println("Lütfen model adını giriniz (Örneğin: C Serisi): ");
        String model = scanner.nextLine().trim().toLowerCase();  // Büyük/Küçük harf duyarlılığı kaldırıldı

        // 3. Parça türü seçimi
        System.out.println("Lütfen parça türünü seçiniz (Kapı, Kaput, Tampon, Far, Stop): ");
        String parcaTuru = scanner.nextLine().trim().toLowerCase();  // Büyük/Küçük harf duyarlılığı kaldırıldı

        // 4. Yön seçimi
        String[] yonSecenekleri;
        if (parcaTuru.equalsIgnoreCase("kapı") || parcaTuru.equalsIgnoreCase("çamurluk")) {
            yonSecenekleri = new String[]{"Sağ Ön", "Sağ Arka", "Sol Ön", "Sol Arka"};
        } else if (parcaTuru.equalsIgnoreCase("far") || parcaTuru.equalsIgnoreCase("stop")) {
            yonSecenekleri = new String[]{"Sağ", "Sol"};
        } else if (parcaTuru.equalsIgnoreCase("kaput") || parcaTuru.equalsIgnoreCase("tampon")) {
            yonSecenekleri = new String[]{"Ön", "Arka"};
        } else {
            System.out.println("Geçersiz parça türü seçildi!");
            return;
        }

        System.out.println("Lütfen yön seçiniz:");
        for (int i = 0; i < yonSecenekleri.length; i++) {
            System.out.println((i + 1) + ". " + yonSecenekleri[i]);
        }

        // Kullanıcıdan sayısal bir girdi bekleniyor
        int yonSecimi = 0;
        boolean gecerliSecim = false;
        while (!gecerliSecim) {
            try {
                // Giriş satır olarak alınır ve tam sayıya dönüştürülür
                String secim = scanner.nextLine();
                yonSecimi = Integer.parseInt(secim);

                // Yön seçiminin geçerli olup olmadığını kontrol et
                if (yonSecimi < 1 || yonSecimi > yonSecenekleri.length) {
                    System.out.println("Geçersiz yön seçimi! Lütfen geçerli bir seçim yapınız.");
                } else {
                    gecerliSecim = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş! Lütfen bir sayı giriniz.");
            }
        }

        String secilenYon = yonSecenekleri[yonSecimi - 1];

        // 5. Resim dosyası bilgileri
        System.out.println("Lütfen resim dosyasının yolunu giriniz (Örneğin: C:/Users/Desktop/resim.jpg): ");
        String resimYolu = scanner.nextLine();
        File kaynakResim = new File(resimYolu);

        // 6. Konum bilgisi
        System.out.println("Lütfen konumu giriniz (Örneğin: Yazıhanenin üstündeki 3. rafta): ");
        String konum = scanner.nextLine();

        // Hedef dizin yapısı
        String hedefDizin = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/markalar/"
                + marka + "/" + model + "/" + parcaTuru + "/" + secilenYon + "/";

        // Resim ve barkod dosyası ismi
        String dosyaAdi = model.replace(" ", "_") + "_" + parcaTuru + "_" + secilenYon.replace(" ", "_").toLowerCase();
        File hedefResim = new File(hedefDizin + dosyaAdi + ".jpg");
        File barkodDosyasi = new File(hedefDizin + dosyaAdi + ".txt");

        try {
            // Hedef dizini oluştur
            File hedefKlasor = new File(hedefDizin);
            if (!hedefKlasor.exists()) {
                hedefKlasor.mkdirs();  // Dizin yoksa oluştur
            }

            // 7. Resmi hedef dizine kopyala
            Files.copy(kaynakResim.toPath(), hedefResim.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Resim başarıyla kaydedildi: " + hedefResim.getPath());

            // 8. Barkod otomatik olarak artırılır
            String barkod = String.format("%013d", barkodNumarasi);  // 13 haneli bir barkod olarak formatla
            barkodNumarasi++;  // Barkod numarasını artır

            // 9. Barkod ve konum bilgisini metin dosyasına yaz
            try (FileWriter writer = new FileWriter(barkodDosyasi)) {
                writer.write("Barkod: " + barkod + "\n");
                writer.write("Konum: " + konum + "\n");
                writer.write("Yön: " + secilenYon + "\n");
                System.out.println("Barkod ve konum bilgisi başarıyla kaydedildi: " + barkodDosyasi.getPath());
            }

        } catch (IOException e) {
            System.out.println("Kayıt işlemi sırasında hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
