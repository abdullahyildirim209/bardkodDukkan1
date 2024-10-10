package AdaYildirim.service;

import AdaYildirim.model.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MarkaKaydetService {

    public static void kaydetMarkaVeModeller(List<Marka> markalar) throws IOException {
        File anaKlasor = new File("markalar");
        if (!anaKlasor.exists()) {
            anaKlasor.mkdirs();
        }

        for (Marka marka : markalar) {
            File markaKlasoru = new File(anaKlasor, marka.getIsim());
            if (!markaKlasoru.exists()) {
                markaKlasoru.mkdirs();
            }

            File modelDosyasi = new File(markaKlasoru, "modeller.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(modelDosyasi))) {
                writer.write("Marka: " + marka.getIsim());
                writer.newLine();
                writer.write("Modeller:");
                writer.newLine();

                for (Model model : marka.getModeller()) {
                    writer.write("- " + model.getModelIsmi());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Modeller kaydedilirken bir hata oluştu: " + e.getMessage());
                throw e;
            }
        }

        System.out.println("Markalar ve modeller başarıyla kaydedildi.");
    }
}
