package AdaYildirim.service;

import AdaYildirim.model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MarkaModellerService {

    public static void kaydetModeller(Marka marka) throws IOException {
        // "markalar" adlı ana klasörün yolu
        String anaKlasorYolu = "C:\\Users\\Abdullah Yusuf\\IdeaProjects\\bardkodDukkan\\bardkodDukkan\\markalar";
        File anaKlasor = new File(anaKlasorYolu);

        if (!anaKlasor.exists()) {
            System.out.println("Ana klasör (markalar) bulunamadı.");
            return;
        }

        // Markaya özel klasör bul veya oluştur (örneğin "markalar/BMW")
        File markaKlasoru = new File(anaKlasor, marka.getIsim());
        if (!markaKlasoru.exists()) {
            markaKlasoru.mkdirs();  // Markaya özel klasör yoksa oluştur
        }

        // Her model için bir klasör oluştur (örneğin "markalar/BMW/X5")
        for (Model model : marka.getModeller()) {
            File modelKlasoru = new File(markaKlasoru, model.getModelIsmi());
            if (!modelKlasoru.exists()) {
                modelKlasoru.mkdirs();  // Eğer model klasörü yoksa oluştur
                System.out.println(model.getModelIsmi() + " adlı model klasörü oluşturuldu: " + modelKlasoru.getAbsolutePath());
            } else {
                System.out.println(model.getModelIsmi() + " adlı model klasörü zaten mevcut.");
            }
        }

        System.out.println(marka.getIsim() + " markasının modelleri başarıyla kaydedildi.");
    }
}