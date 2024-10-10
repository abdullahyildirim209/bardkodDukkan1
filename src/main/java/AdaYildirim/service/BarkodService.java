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

    public static void barkodOlustur(String data, String fileName) throws Exception {
        BarcodeFormat format = BarcodeFormat.CODE_128;

        int width = 300;
        int height = 100;

        BitMatrix matrix = new MultiFormatWriter().encode(data, format, width, height);

        String barkodKlasor = "barkodlar";

        Path klasorPath = Paths.get(barkodKlasor);
        if (!Files.exists(klasorPath)) {
            Files.createDirectory(klasorPath);
        }

        Path barkodYolu = Paths.get(barkodKlasor, fileName + ".png");

        MatrixToImageWriter.writeToPath(matrix, "png", barkodYolu);

        System.out.println("Barkod başarıyla kaydedildi: " + barkodYolu.toString());
    }
}
