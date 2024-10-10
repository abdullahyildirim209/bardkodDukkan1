package AdaYildirim.service;

import AdaYildirim.model.Parca;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ParcaKaydetService {

    public static void barkodOlustur(String data, String filePath) throws Exception {
        BarcodeFormat format = BarcodeFormat.CODE_128;
        int width = 300;
        int height = 100;

        BitMatrix matrix = new MultiFormatWriter().encode(data, format, width, height);
        Path path = Paths.get(filePath);
        MatrixToImageWriter.writeToPath(matrix, "png", path);
    }

    public static void kaydet(List<Parca> parcalar) throws IOException {
        for (Parca parca : parcalar) {
            String markaAdi = parca.getMarka().getIsim();
            String kategori = parca.getKategori();
            String parcaIsmi = parca.getIsim();
            String barkod = parca.getBarkod();

            File markaKlasoru = new File("markalar/" + markaAdi);
            if (!markaKlasoru.exists()) {
                markaKlasoru.mkdirs();
            }

            File barkodKlasoru = new File(markaKlasoru, "barkodlar");
            if (!barkodKlasoru.exists()) {
                barkodKlasoru.mkdirs();
            }

            String barkodDosyaAdi = barkodKlasoru.getAbsolutePath() + "/" + parcaIsmi.replace(" ", "_") + "_barkod.png";
            try {
                barkodOlustur(barkod, barkodDosyaAdi);
                System.out.println("Barkod başarıyla kaydedildi: " + barkodDosyaAdi);
            } catch (Exception e) {
                System.out.println("Barkod oluşturulurken hata oluştu: " + e.getMessage());
            }

            File kategoriDosyasi = new File(markaKlasoru, kategori + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(kategoriDosyasi, true))) {
                writer.write("Parça: " + parcaIsmi + ", Barkod: " + barkod);
                writer.newLine();
            } catch (IOException e) {
                System.out.println("Parça kaydedilirken bir hata oluştu: " + e.getMessage());
            }
        }

        System.out.println("Parçalar ve barkodlar başarıyla kaydedildi.");
    }
}
