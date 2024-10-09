package AdaYildirim;
/*
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class App {

    // Barkod numarasını tutacağımız dosyanın yolu
    static String barkodDosyaYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/barkod_numarasi.txt";

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

        // 7. Barkod numarasını oku
        int barkodNumarasi = barkodOkuVeArttir();  // Barkod numarasını oku ve bir artır

        // Barkod numarasını string olarak formatla (13 haneli)
        String barkod = String.format("%013d", barkodNumarasi);

        // Hedef dizin yapısı
        String hedefDizin = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/markalar/"
                + marka + "/" + model + "/" + parcaTuru + "/" + secilenYon + "/";

        // Resim ve barkod dosyası ismi (barkod numarasını dosya ismi olarak kullanıyoruz)
        File hedefResim = new File(hedefDizin + barkod + ".jpg");
        File barkodDosyasi = new File(hedefDizin + barkod + ".txt");

        try {
            // Hedef dizini oluştur
            File hedefKlasor = new File(hedefDizin);
            if (!hedefKlasor.exists()) {
                hedefKlasor.mkdirs();  // Dizin yoksa oluştur
            }

            // 8. Resmi hedef dizine kopyala
            Files.copy(kaynakResim.toPath(), hedefResim.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Resim başarıyla kaydedildi: " + hedefResim.getPath());

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

    // Barkod numarasını okur ve bir artırarak geri döner
    private static int barkodOkuVeArttir() {
        int barkodNumarasi = 1;  // Varsayılan başlangıç değeri
        File barkodDosyasi = new File(barkodDosyaYolu);

        // Barkod numarasını dosyadan oku
        if (barkodDosyasi.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(barkodDosyasi))) {
                String satir = reader.readLine();
                if (satir != null) {
                    barkodNumarasi = Integer.parseInt(satir);  // Mevcut barkod numarasını oku
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Barkod numarası okunurken hata oluştu: " + e.getMessage());
            }
        }

        // Barkod numarasını bir artır ve dosyaya yaz
        try (FileWriter writer = new FileWriter(barkodDosyasi)) {
            writer.write(String.valueOf(barkodNumarasi + 1));  // Bir artırılmış barkod numarasını kaydet
        } catch (IOException e) {
            System.out.println("Barkod numarası kaydedilirken hata oluştu: " + e.getMessage());
        }

        return barkodNumarasi;
    }
}*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class App extends JFrame {

    private JTextField markaField, modelField, konumField;
    private JComboBox<String> parcaTurCombo, yonCombo;
    private JButton resimSecButton, kaydetButton;
    private JLabel secilenResimYoluLabel;
    private File secilenResim;

    // Barkod numarasını tutacağımız dosyanın yolu
    static String barkodDosyaYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/barkod_numarasi.txt";

    public App() {
        setTitle("Parça Kayıt Sistemi");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Merkeze yerleştir
        setLayout(new GridLayout(8, 2, 10, 10));

        // Marka ve Model
        add(new JLabel("Marka:"));
        markaField = new JTextField();
        add(markaField);

        add(new JLabel("Model:"));
        modelField = new JTextField();
        add(modelField);

        // Parça Türü
        add(new JLabel("Parça Türü:"));
        String[] parcaTurleri = {"Kapı", "Kaput", "Tampon", "Far", "Stop"};
        parcaTurCombo = new JComboBox<>(parcaTurleri);
        add(parcaTurCombo);

        // Yön
        add(new JLabel("Yön:"));
        yonCombo = new JComboBox<>();
        updateYonCombo();  // Yön seçeneklerini güncelle
        parcaTurCombo.addActionListener(e -> updateYonCombo());
        add(yonCombo);

        // Konum
        add(new JLabel("Konum:"));
        konumField = new JTextField();
        add(konumField);

        // Resim Seçici
        add(new JLabel("Resim Seç:"));
        resimSecButton = new JButton("Resim Seç");
        resimSecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resimSec();
            }
        });
        add(resimSecButton);
        secilenResimYoluLabel = new JLabel("Seçilen dosya: ");
        add(secilenResimYoluLabel);

        // Kaydet Butonu
        kaydetButton = new JButton("Kaydet");
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kaydetIslemi();
            }
        });
        add(kaydetButton);

        setVisible(true);
    }

    private void updateYonCombo() {
        String secilenParcaTuru = (String) parcaTurCombo.getSelectedItem();
        yonCombo.removeAllItems();
        if (secilenParcaTuru.equalsIgnoreCase("Kapı") || secilenParcaTuru.equalsIgnoreCase("Çamurluk")) {
            yonCombo.addItem("Sağ Ön");
            yonCombo.addItem("Sağ Arka");
            yonCombo.addItem("Sol Ön");
            yonCombo.addItem("Sol Arka");
        } else if (secilenParcaTuru.equalsIgnoreCase("Far") || secilenParcaTuru.equalsIgnoreCase("Stop")) {
            yonCombo.addItem("Sağ");
            yonCombo.addItem("Sol");
        } else if (secilenParcaTuru.equalsIgnoreCase("Kaput") || secilenParcaTuru.equalsIgnoreCase("Tampon")) {
            yonCombo.addItem("Ön");
            yonCombo.addItem("Arka");
        }
    }

    private void resimSec() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            secilenResim = fileChooser.getSelectedFile();
            secilenResimYoluLabel.setText("Seçilen dosya: " + secilenResim.getAbsolutePath());
        }
    }

    private void kaydetIslemi() {
        String marka = markaField.getText().trim().toLowerCase();
        String model = modelField.getText().trim().toLowerCase();
        String parcaTuru = ((String) parcaTurCombo.getSelectedItem()).toLowerCase();
        String yon = (String) yonCombo.getSelectedItem();
        String konum = konumField.getText();

        if (secilenResim == null) {
            JOptionPane.showMessageDialog(this, "Lütfen bir resim seçin.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 1. Barkod numarasını oku
        int barkodNumarasi = barkodOkuVeArttir();

        // 2. Dosya yapısını oluştur
        String barkod = String.format("%013d", barkodNumarasi); // Barkod numarasını formatla
        String hedefDizin = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/markalar/"
                + marka + "/" + model + "/" + parcaTuru + "/" + yon + "/";

        File hedefKlasor = new File(hedefDizin);
        if (!hedefKlasor.exists()) {
            hedefKlasor.mkdirs();
        }

        // 3. Resmi kopyala
        File hedefResim = new File(hedefDizin + barkod + ".jpg");
        try {
            Files.copy(secilenResim.toPath(), hedefResim.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Resim kopyalanırken hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. Barkod bilgilerini yaz
        File barkodDosyasi = new File(hedefDizin + barkod + ".txt");
        try (FileWriter writer = new FileWriter(barkodDosyasi)) {
            writer.write("Barkod: " + barkod + "\n");
            writer.write("Konum: " + konum + "\n");
            writer.write("Yön: " + yon + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Barkod dosyası yazılırken hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Parça başarıyla kaydedildi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
    }

    // Barkod numarasını okur ve bir artırarak geri döner
    private static int barkodOkuVeArttir() {
        int barkodNumarasi = 1;  // Varsayılan başlangıç değeri
        File barkodDosyasi = new File(barkodDosyaYolu);

        // Barkod numarasını dosyadan oku
        if (barkodDosyasi.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(barkodDosyasi))) {
                String satir = reader.readLine();
                if (satir != null) {
                    barkodNumarasi = Integer.parseInt(satir);  // Mevcut barkod numarasını oku
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Barkod numarası okunurken hata oluştu: " + e.getMessage());
            }
        }

        // Barkod numarasını bir artır ve dosyaya yaz
        try (FileWriter writer = new FileWriter(barkodDosyasi)) {
            writer.write(String.valueOf(barkodNumarasi + 1));  // Bir artırılmış barkod numarasını kaydet
        } catch (IOException e) {
            System.out.println("Barkod numarası kaydedilirken hata oluştu: " + e.getMessage());
        }

        return barkodNumarasi;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
