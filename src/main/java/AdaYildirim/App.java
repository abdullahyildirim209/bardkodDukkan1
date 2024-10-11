package AdaYildirim;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class App extends JFrame {

    private JTextField konumField;
    private JComboBox<String> markaCombo, modelCombo, parcaTurCombo, yonCombo;
    private JButton resimSecButton, kaydetButton;
    private JLabel secilenResimYoluLabel;
    private File secilenResim;
    private BufferedImage backgroundImage;

    static String barkodDosyaYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/barkod_numarasi.txt";

    private Map<String, ArrayList<String>> markaModelMap = new HashMap<>();

    public App() {
        setTitle("Ada Yıldırım Otomotiv");
        setSize(800, 700); // Yüksekliği artırdık
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Arka planı ayarlıyoruz
        try {
            //backgroundImage = ImageIO.read(new File("C:/Users/Abdullah Yusuf/Desktop/Ekran Alıntısı.PNG"));
            backgroundImage = ImageIO.read(new File("C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/Ekran Alıntısı2.PNG"));// Arka plan dosyasını doğru yola koy
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Özel JPanel ile arka planı çizen panel
        JPanel arkaPlanPaneli = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Arka planı çiz
                }
            }
        };
        arkaPlanPaneli.setLayout(new GridLayout(9, 2, 10, 10));
        setContentPane(arkaPlanPaneli);

        loadMarkalarAndModeller();

        arkaPlanPaneli.add(new JLabel("Marka:"));
        markaCombo = new JComboBox<>(markaModelMap.keySet().toArray(new String[0]));
        markaCombo.addActionListener(e -> updateModelCombo());
        arkaPlanPaneli.add(markaCombo);

        arkaPlanPaneli.add(new JLabel("Model:"));
        modelCombo = new JComboBox<>();
        updateModelCombo();
        arkaPlanPaneli.add(modelCombo);

        arkaPlanPaneli.add(new JLabel("Parça Türü:"));
        String[] parcaTurleri = {"Kapı", "Kaput", "Tampon", "Far", "Stop"};
        parcaTurCombo = new JComboBox<>(parcaTurleri);
        parcaTurCombo.addActionListener(e -> updateYonCombo());
        arkaPlanPaneli.add(parcaTurCombo);

        arkaPlanPaneli.add(new JLabel("Yön:"));
        yonCombo = new JComboBox<>();
        updateYonCombo();
        arkaPlanPaneli.add(yonCombo);

        arkaPlanPaneli.add(new JLabel("Konum:"));
        konumField = new JTextField();
        arkaPlanPaneli.add(konumField);

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

        kaydetButton = new JButton("Kaydet");
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kaydetIslemi();
            }
        });
        arkaPlanPaneli.add(kaydetButton);

        // Alt tarafa yazıyı ekle
        JLabel altYazi = new JLabel("Ada Yıldırım Otomotiv", SwingConstants.CENTER);
        altYazi.setFont(new Font("Serif", Font.BOLD, 20));
        altYazi.setForeground(Color.BLACK);
        add(altYazi, BorderLayout.SOUTH); // Yazıyı en alta ekle




        setVisible(true);
    }

    private void loadMarkalarAndModeller() {
        String markalarDiziniYolu = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/markalar";
        File markalarDizini = new File(markalarDiziniYolu);

        if (markalarDizini.exists() && markalarDizini.isDirectory()) {
            File[] markaKlasorleri = markalarDizini.listFiles(File::isDirectory);
            if (markaKlasorleri != null) {
                for (File markaKlasoru : markaKlasorleri) {
                    String markaIsmi = markaKlasoru.getName();
                    ArrayList<String> modeller = new ArrayList<>();

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
        // Kullanıcıya "ArabaParcaları" klasörünü varsayılan olarak açtır
        JFileChooser fileChooser = new JFileChooser(new File("C:/Users/Abdullah Yusuf/Desktop/ArabaParcaları"));
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

        int barkodNumarasi = barkodOkuVeArttir();

        String barkod = String.format("%013d", barkodNumarasi); // Barkod numarasını formatla
        String hedefDizin = "C:/Users/Abdullah Yusuf/IdeaProjects/bardkodDukkan/bardkodDukkan/markalar/"
                + marka + "/" + model + "/" + parcaTuru + "/" + yon + "/";

        File hedefKlasor = new File(hedefDizin);
        if (!hedefKlasor.exists()) {
            hedefKlasor.mkdirs();
        }

        File hedefResim = new File(hedefDizin + barkod + ".jpg");
        try {
            Files.copy(secilenResim.toPath(), hedefResim.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Resim kopyalanırken hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File barkodDosyasi = new File(hedefDizin + barkod + ".txt");
        try (FileWriter writer = new FileWriter(barkodDosyasi)) {
            writer.write("Barkod: " + barkod + "\n");
            writer.write("Konum: " + konum + "\n");
            writer.write("Yön: " + yon + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Barkod dosyası yazılırken hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Path barkodImagePath = Paths.get(hedefDizin + barkod + ".png");
            createBarcodeImage(barkod, barkodImagePath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Barkod PNG dosyası oluşturulurken hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Parça başarıyla kaydedildi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
    }

    private void createBarcodeImage(String data, Path outputPath) throws Exception {
        int width = 300;
        int height = 100;
        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, width, height);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", outputPath);
    }

    private static int barkodOkuVeArttir() {
        int barkodNumarasi = 1;
        File barkodDosyasi = new File(barkodDosyaYolu);

        if (barkodDosyasi.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(barkodDosyasi))) {
                String satir = reader.readLine();
                if (satir != null) {
                    barkodNumarasi = Integer.parseInt(satir);
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Barkod numarası okunurken hata oluştu: " + e.getMessage());
            }
        }

        try (FileWriter writer = new FileWriter(barkodDosyasi)) {
            writer.write(String.valueOf(barkodNumarasi + 1));
        } catch (IOException e) {
            System.out.println("Barkod numarası kaydedilirken hata oluştu: " + e.getMessage());
        }

        return barkodNumarasi;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}