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

/*import javax.swing.*;
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
}*/

/*import javax.swing.*;
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

        // Arka plana özel bir panel ekliyoruz
        JPanel arkaPlanPaneli = new CustomPanel();
        arkaPlanPaneli.setLayout(new GridLayout(8, 2, 10, 10)); // Bileşenlerin düzenini GridLayout ile ayarlıyoruz
        setContentPane(arkaPlanPaneli);

        // Marka ve Model
        arkaPlanPaneli.add(new JLabel("Marka:"));
        markaField = new JTextField();
        arkaPlanPaneli.add(markaField);

        arkaPlanPaneli.add(new JLabel("Model:"));
        modelField = new JTextField();
        arkaPlanPaneli.add(modelField);

        // Parça Türü
        arkaPlanPaneli.add(new JLabel("Parça Türü:"));
        String[] parcaTurleri = {"Kapı", "Kaput", "Tampon", "Far", "Stop"};
        parcaTurCombo = new JComboBox<>(parcaTurleri);
        arkaPlanPaneli.add(parcaTurCombo);

        // Yön
        arkaPlanPaneli.add(new JLabel("Yön:"));
        yonCombo = new JComboBox<>();
        updateYonCombo();  // Yön seçeneklerini güncelle
        parcaTurCombo.addActionListener(e -> updateYonCombo());
        arkaPlanPaneli.add(yonCombo);

        // Konum
        arkaPlanPaneli.add(new JLabel("Konum:"));
        konumField = new JTextField();
        arkaPlanPaneli.add(konumField);

        // Resim Seçici
        arkaPlanPaneli.add(new JLabel("Resim Seç:"));
        resimSecButton = new JButton("Resim Seç");
        resimSecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resimSec();
            }
        });
        arkaPlanPaneli.add(resimSecButton);
        secilenResimYoluLabel = new JLabel("Seçilen dosya: ");
        arkaPlanPaneli.add(secilenResimYoluLabel);

        // Kaydet Butonu
        kaydetButton = new JButton("Kaydet");
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kaydetIslemi();
            }
        });
        arkaPlanPaneli.add(kaydetButton);

        setVisible(true);
    }

    // Arka planda özel yazı yazdırmak için JPanel'i genişletiyoruz
    private static class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Arka plan rengi
            setBackground(Color.LIGHT_GRAY);

            // "AdaYıldırım" yazısını eklemek
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawString("AdaYıldırım", 150, 50); // Yazının konumunu belirliyoruz
        }
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
}*/

/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame {

    private JTextField konumField;
    private JComboBox<String> markaCombo, modelCombo, parcaTurCombo, yonCombo;
    private JButton resimSecButton, kaydetButton;
    private JLabel secilenResimYoluLabel;
    private File secilenResim;

    // Barkod numarasını tutacağımız dosyanın yolu
    static String barkodDosyaYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/barkod_numarasi.txt";

    // Markalar ve modelleri tutmak için HashMap
    private Map<String, ArrayList<String>> markaModelMap = new HashMap<>();

    public App() {
        setTitle("Parça Kayıt Sistemi");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Merkeze yerleştir

        // Arka plan için özel bir panel
        JPanel arkaPlanPaneli = new CustomPanel();
        arkaPlanPaneli.setLayout(new GridLayout(9, 2, 10, 10)); // Bileşenlerin düzenini ayarla
        setContentPane(arkaPlanPaneli);

        // Marka ve Model Seçimi
        loadMarkalarAndModeller();  // Dosya sisteminden markaları ve modelleri yükle

        arkaPlanPaneli.add(new JLabel("Marka:"));
        markaCombo = new JComboBox<>(markaModelMap.keySet().toArray(new String[0]));
        markaCombo.addActionListener(e -> updateModelCombo());  // Model seçeneklerini güncelle
        arkaPlanPaneli.add(markaCombo);

        arkaPlanPaneli.add(new JLabel("Model:"));
        modelCombo = new JComboBox<>();
        updateModelCombo();  // Başlangıçta model listesini güncelle
        arkaPlanPaneli.add(modelCombo);

        // Parça Türü Seçimi
        arkaPlanPaneli.add(new JLabel("Parça Türü:"));
        String[] parcaTurleri = {"Kapı", "Kaput", "Tampon", "Far", "Stop"};
        parcaTurCombo = new JComboBox<>(parcaTurleri);
        parcaTurCombo.addActionListener(e -> updateYonCombo());  // Yön seçeneklerini güncelle
        arkaPlanPaneli.add(parcaTurCombo);

        // Yön Seçimi
        arkaPlanPaneli.add(new JLabel("Yön:"));
        yonCombo = new JComboBox<>();
        updateYonCombo();  // Başlangıçta yön listesini güncelle
        arkaPlanPaneli.add(yonCombo);

        // Konum Seçimi
        arkaPlanPaneli.add(new JLabel("Konum:"));
        konumField = new JTextField();
        arkaPlanPaneli.add(konumField);

        // Resim Seçici
        arkaPlanPaneli.add(new JLabel("Resim Seç:"));
        resimSecButton = new JButton("Resim Seç");
        resimSecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resimSec();
            }
        });
        arkaPlanPaneli.add(resimSecButton);
        secilenResimYoluLabel = new JLabel("Seçilen dosya: ");
        arkaPlanPaneli.add(secilenResimYoluLabel);

        // Kaydet Butonu
        kaydetButton = new JButton("Kaydet");
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kaydetIslemi();
            }
        });
        arkaPlanPaneli.add(kaydetButton);

        setVisible(true);
    }

    // Markalar ve modelleri dosya sisteminden yükle
    private void loadMarkalarAndModeller() {
        String markalarDiziniYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/markalar";
        File markalarDizini = new File(markalarDiziniYolu);

        if (markalarDizini.exists() && markalarDizini.isDirectory()) {
            // Marka klasörlerini oku
            File[] markaKlasorleri = markalarDizini.listFiles(File::isDirectory);
            if (markaKlasorleri != null) {
                for (File markaKlasoru : markaKlasorleri) {
                    String markaIsmi = markaKlasoru.getName();
                    ArrayList<String> modeller = new ArrayList<>();

                    // Her marka klasörü içindeki model klasörlerini oku
                    File[] modelKlasorleri = markaKlasoru.listFiles(File::isDirectory);
                    if (modelKlasorleri != null) {
                        for (File modelKlasoru : modelKlasorleri) {
                            modeller.add(modelKlasoru.getName());
                        }
                    }
                    markaModelMap.put(markaIsmi, modeller);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Markalar dizini bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Arka plan panelinde "AdaYıldırım" yazısını en alta çizmek için CustomPanel sınıfı
    private static class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.LIGHT_GRAY);

            // "AdaYıldırım" yazısını çizmek
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.setColor(Color.DARK_GRAY);

            // Yazının boyutlarını hesapla
            FontMetrics fm = g2d.getFontMetrics();
            String yazı = "AdaYıldırım";
            int x = (getWidth() - fm.stringWidth(yazı)) / 2;
            int y = getHeight() - fm.getHeight();  // Yükseklik olarak en altı kullanıyoruz

            // Yazıyı çiz
            g2d.drawString(yazı, x, y);
        }
    }

    // Marka değiştiğinde model listesini güncelle
    private void updateModelCombo() {
        String secilenMarka = (String) markaCombo.getSelectedItem();
        ArrayList<String> modeller = markaModelMap.get(secilenMarka);

        modelCombo.removeAllItems();
        if (modeller != null) {
            for (String model : modeller) {
                modelCombo.addItem(model);
            }
        }
    }

    // Parça türüne göre yön seçeneklerini güncelle
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
        String marka = ((String) markaCombo.getSelectedItem()).toLowerCase();
        String model = ((String) modelCombo.getSelectedItem()).toLowerCase();
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
}*/

/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame {

    private JTextField konumField;
    private JComboBox<String> markaCombo, modelCombo, parcaTurCombo, yonCombo;
    private JButton resimSecButton, kaydetButton;
    private JLabel secilenResimYoluLabel;
    private File secilenResim;
    private BufferedImage backgroundImage;

    // Barkod numarasını tutacağımız dosyanın yolu
    static String barkodDosyaYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/barkod_numarasi.txt";

    // Markalar ve modelleri tutmak için HashMap
    private Map<String, ArrayList<String>> markaModelMap = new HashMap<>();

    public App() {
        setTitle("Parça Kayıt Sistemi");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Merkeze yerleştir

        // Arka plan için özel bir panel
        JPanel arkaPlanPaneli = new CustomPanel();
        arkaPlanPaneli.setLayout(new GridLayout(9, 2, 10, 10)); // Bileşenlerin düzenini ayarla
        setContentPane(arkaPlanPaneli);

        // Marka ve Model Seçimi
        loadMarkalarAndModeller();  // Dosya sisteminden markaları ve modelleri yükle

        arkaPlanPaneli.add(new JLabel("Marka:"));
        markaCombo = new JComboBox<>(markaModelMap.keySet().toArray(new String[0]));
        markaCombo.addActionListener(e -> updateModelCombo());  // Model seçeneklerini güncelle
        arkaPlanPaneli.add(markaCombo);

        arkaPlanPaneli.add(new JLabel("Model:"));
        modelCombo = new JComboBox<>();
        updateModelCombo();  // Başlangıçta model listesini güncelle
        arkaPlanPaneli.add(modelCombo);

        // Parça Türü Seçimi
        arkaPlanPaneli.add(new JLabel("Parça Türü:"));
        String[] parcaTurleri = {"Kapı", "Kaput", "Tampon", "Far", "Stop"};
        parcaTurCombo = new JComboBox<>(parcaTurleri);
        parcaTurCombo.addActionListener(e -> updateYonCombo());  // Yön seçeneklerini güncelle
        arkaPlanPaneli.add(parcaTurCombo);

        // Yön Seçimi
        arkaPlanPaneli.add(new JLabel("Yön:"));
        yonCombo = new JComboBox<>();
        updateYonCombo();  // Başlangıçta yön listesini güncelle
        arkaPlanPaneli.add(yonCombo);

        // Konum Seçimi
        arkaPlanPaneli.add(new JLabel("Konum:"));
        konumField = new JTextField();
        arkaPlanPaneli.add(konumField);

        // Resim Seçici
        arkaPlanPaneli.add(new JLabel("Resim Seç:"));
        resimSecButton = new JButton("Resim Seç");
        resimSecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resimSec();
            }
        });
        arkaPlanPaneli.add(resimSecButton);
        secilenResimYoluLabel = new JLabel("Seçilen dosya: ");
        arkaPlanPaneli.add(secilenResimYoluLabel);

        // Kaydet Butonu
        kaydetButton = new JButton("Kaydet");
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kaydetIslemi();
            }
        });
        arkaPlanPaneli.add(kaydetButton);

        // Arka plan resmi yükleme
        try {
            backgroundImage = ImageIO.read(new File("C:/Users/Abdullah Yusuf/Desktop/resim.jpg")); // Resmi uygun yere koy
        } catch (IOException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    // Markalar ve modelleri dosya sisteminden yükle
    private void loadMarkalarAndModeller() {
        String markalarDiziniYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/markalar";
        File markalarDizini = new File(markalarDiziniYolu);

        if (markalarDizini.exists() && markalarDizini.isDirectory()) {
            // Marka klasörlerini oku
            File[] markaKlasorleri = markalarDizini.listFiles(File::isDirectory);
            if (markaKlasorleri != null) {
                for (File markaKlasoru : markaKlasorleri) {
                    String markaIsmi = markaKlasoru.getName();
                    ArrayList<String> modeller = new ArrayList<>();

                    // Her marka klasörü içindeki model klasörlerini oku
                    File[] modelKlasorleri = markaKlasoru.listFiles(File::isDirectory);
                    if (modelKlasorleri != null) {
                        for (File modelKlasoru : modelKlasorleri) {
                            modeller.add(modelKlasoru.getName());
                        }
                    }
                    markaModelMap.put(markaIsmi, modeller);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Markalar dizini bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Arka plan panelinde "AdaYıldırım" yazısını ve arka plan resmini çizen CustomPanel sınıfı
    private class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Arka plan resmi çiz
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            // "AdaYıldırım" yazısını çiz
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.setColor(Color.DARK_GRAY);

            // Yazının boyutlarını hesapla
            FontMetrics fm = g2d.getFontMetrics();
            String yazı = "AdaYıldırım";
            int x = (getWidth() - fm.stringWidth(yazı)) / 2;
            int y = getHeight() - fm.getHeight();  // Yükseklik olarak en altı kullanıyoruz

            // Yazıyı çiz
            g2d.drawString(yazı, x, y);
        }
    }

    // Marka değiştiğinde model listesini güncelle
    private void updateModelCombo() {
        String secilenMarka = (String) markaCombo.getSelectedItem();
        ArrayList<String> modeller = markaModelMap.get(secilenMarka);

        modelCombo.removeAllItems();
        if (modeller != null) {
            for (String model : modeller) {
                modelCombo.addItem(model);
            }
        }
    }

    // Parça türüne göre yön seçeneklerini güncelle
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
        String marka = ((String) markaCombo.getSelectedItem()).toLowerCase();
        String model = ((String) modelCombo.getSelectedItem()).toLowerCase();
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
}*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame {

    private JTextField konumField;
    private JComboBox<String> markaCombo, modelCombo, parcaTurCombo, yonCombo;
    private JButton resimSecButton, kaydetButton;
    private JLabel secilenResimYoluLabel;
    private File secilenResim;
    private BufferedImage backgroundImage;

    // Barkod numarasını tutacağımız dosyanın yolu
    static String barkodDosyaYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/barkod_numarasi.txt";

    // Markalar ve modelleri tutmak için HashMap
    private Map<String, ArrayList<String>> markaModelMap = new HashMap<>();

    public App() {
        setTitle("Parça Kayıt Sistemi");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Merkeze yerleştir

        // Arka plan için özel bir panel
        JPanel arkaPlanPaneli = new CustomPanel();
        arkaPlanPaneli.setLayout(new GridLayout(9, 2, 10, 10)); // Bileşenlerin düzenini ayarla
        setContentPane(arkaPlanPaneli);

        // Marka ve Model Seçimi
        loadMarkalarAndModeller();  // Dosya sisteminden markaları ve modelleri yükle

        arkaPlanPaneli.add(new JLabel("Marka:"));
        markaCombo = new JComboBox<>(markaModelMap.keySet().toArray(new String[0]));
        markaCombo.addActionListener(e -> updateModelCombo());  // Model seçeneklerini güncelle
        arkaPlanPaneli.add(markaCombo);

        arkaPlanPaneli.add(new JLabel("Model:"));
        modelCombo = new JComboBox<>();
        updateModelCombo();  // Başlangıçta model listesini güncelle
        arkaPlanPaneli.add(modelCombo);

        // Parça Türü Seçimi
        arkaPlanPaneli.add(new JLabel("Parça Türü:"));
        String[] parcaTurleri = {"Kapı", "Kaput", "Tampon", "Far", "Stop"};
        parcaTurCombo = new JComboBox<>(parcaTurleri);
        parcaTurCombo.addActionListener(e -> updateYonCombo());  // Yön seçeneklerini güncelle
        arkaPlanPaneli.add(parcaTurCombo);

        // Yön Seçimi
        arkaPlanPaneli.add(new JLabel("Yön:"));
        yonCombo = new JComboBox<>();
        updateYonCombo();  // Başlangıçta yön listesini güncelle
        arkaPlanPaneli.add(yonCombo);

        // Konum Seçimi
        arkaPlanPaneli.add(new JLabel("Konum:"));
        konumField = new JTextField();
        arkaPlanPaneli.add(konumField);

        // Resim Seçici
        arkaPlanPaneli.add(new JLabel("Resim Seç:"));
        resimSecButton = new JButton("Resim Seç");
        resimSecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resimSec();
            }
        });
        arkaPlanPaneli.add(resimSecButton);
        secilenResimYoluLabel = new JLabel("Seçilen dosya: ");
        arkaPlanPaneli.add(secilenResimYoluLabel);

        // Kaydet Butonu
        kaydetButton = new JButton("Kaydet");
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kaydetIslemi();
            }
        });
        arkaPlanPaneli.add(kaydetButton);

        // Arka plan resmi yükleme
        try {
            backgroundImage = ImageIO.read(new File("C:/Users/Abdullah Yusuf/Desktop/resim.jpg")); // Resmi uygun yere koy
        } catch (IOException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    // Markalar ve modelleri dosya sisteminden yükle
    private void loadMarkalarAndModeller() {
        String markalarDiziniYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/markalar";
        File markalarDizini = new File(markalarDiziniYolu);

        if (markalarDizini.exists() && markalarDizini.isDirectory()) {
            // Marka klasörlerini oku
            File[] markaKlasorleri = markalarDizini.listFiles(File::isDirectory);
            if (markaKlasorleri != null) {
                for (File markaKlasoru : markaKlasorleri) {
                    String markaIsmi = markaKlasoru.getName();
                    ArrayList<String> modeller = new ArrayList<>();

                    // Her marka klasörü içindeki model klasörlerini oku
                    File[] modelKlasorleri = markaKlasoru.listFiles(File::isDirectory);
                    if (modelKlasorleri != null) {
                        for (File modelKlasoru : modelKlasorleri) {
                            modeller.add(modelKlasoru.getName());
                        }
                    }
                    markaModelMap.put(markaIsmi, modeller);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Markalar dizini bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Arka plan panelinde "AdaYıldırım" yazısını ve arka plan resmini çizen CustomPanel sınıfı
    private class CustomPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Arka plan resmi çiz
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            // "AdaYıldırım" yazısını çiz
            Graphics2D g2d = (Graphics2D) g;
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.setColor(Color.DARK_GRAY);

            // Yazının boyutlarını hesapla
            FontMetrics fm = g2d.getFontMetrics();
            String yazı = "AdaYıldırım";
            int x = (getWidth() - fm.stringWidth(yazı)) / 2;
            int y = getHeight() - fm.getHeight();  // Yükseklik olarak en altı kullanıyoruz

            // Yazıyı çiz
            g2d.drawString(yazı, x, y);
        }
    }

    // Marka değiştiğinde model listesini güncelle
    private void updateModelCombo() {
        String secilenMarka = (String) markaCombo.getSelectedItem();
        ArrayList<String> modeller = markaModelMap.get(secilenMarka);

        modelCombo.removeAllItems();
        if (modeller != null) {
            for (String model : modeller) {
                modelCombo.addItem(model);
            }
        }
    }

    // Parça türüne göre yön seçeneklerini güncelle
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
        String marka = ((String) markaCombo.getSelectedItem()).toLowerCase();
        String model = ((String) modelCombo.getSelectedItem()).toLowerCase();
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
