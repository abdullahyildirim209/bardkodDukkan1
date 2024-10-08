package AdaYildirim.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BarkodService {

    // Barkod oluşturup bir klasöre kaydetmek
    public static void barkodOlustur(String data, String fileName) throws Exception {
        // Barkod için kullanılan format
        BarcodeFormat format = BarcodeFormat.CODE_128;

        // Barkod resminin boyutları
        int width = 300;
        int height = 100;

        // Barkod resmini oluşturma
        BitMatrix matrix = new MultiFormatWriter().encode(data, format, width, height);

        // Barkodları kaydedeceğimiz klasörün yolu
        String barkodKlasor = "barkodlar";  // Klasör adını burada belirtiyoruz

        // Klasör var mı kontrol et, yoksa oluştur
        Path klasorPath = Paths.get(barkodKlasor);
        if (!Files.exists(klasorPath)) {
            Files.createDirectory(klasorPath);  // Klasör yoksa oluştur
        }

        // Barkod dosyasının tam yolu (barkodlar/klasor/fileName.png)
        Path barkodYolu = Paths.get(barkodKlasor, fileName + ".png");

        // Barkodu PNG formatında kaydet
        MatrixToImageWriter.writeToPath(matrix, "png", barkodYolu);

        System.out.println("Barkod başarıyla kaydedildi: " + barkodYolu.toString());
    }
}
