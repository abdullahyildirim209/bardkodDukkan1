package AdaYildirim.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ModelDosyasiService {

    public static void modelDosyasiOlustur() throws IOException {
        File modelDosyasi = new File("modeller");
        if (!modelDosyasi.exists()) {
            modelDosyasi.createNewFile();  // Eğer dosya yoksa oluştur
        }

        System.out.println("Modeller dosyası oluşturuldu: " + modelDosyasi.getAbsolutePath());
    }

    public static void parcayiDosyayaEkle(String parcaBilgisi) throws IOException {
        File modelDosyasi = new File("modeller");
        if (!modelDosyasi.exists()) {
            System.out.println("Modeller dosyası bulunamadı, önce oluşturulması gerekiyor.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(modelDosyasi, true))) {
            writer.write(parcaBilgisi);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Parça dosyaya yazılamadı: " + e.getMessage());
        }
    }
}