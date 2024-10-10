# Barkod Dükkan Uygulaması

Barkod Dükkan Uygulaması, kullanıcıların otomobil parçalarını barkodlayarak kaydetmelerine ve yönetmelerine olanak tanıyan bir masaüstü Java uygulamasıdır. Bu proje, farklı otomobil markalarına ve modellere göre parçaları organize eder ve her bir parça için barkod, resim ve konum bilgisi kaydetmenizi sağlar.

## Özellikler

- Farklı otomobil markalarına ve modellere göre parçaların kaydedilmesi.
- Parça türü (Kapı, Far, Tampon vb.) ve yön (Sağ Ön, Sol Arka vb.) seçimi.
- Her parça için barkod oluşturma ve kaydetme.
- Parça resmini ve barkodunu belirli bir klasör yapısına göre kaydetme.
- Parça konum bilgisi ekleyebilme.
- Arka planda özel bir resim gösterme ve uygulama içinde grafiksel bileşenler.

## Gereksinimler

- Java 8 veya daha üstü bir sürüm
- Bir IDE (IntelliJ IDEA, Eclipse vb.)
- Git (tercihen)
- Resim dosyalarını kaydedebilmek için `javax.imageio` kütüphanesi

## Kurulum

1. **Projeyi Klonla:**

   Eğer Git kullanıyorsanız, projeyi klonlayabilirsiniz:

   ```bash
   git clone https://github.com/kullanici_adiniz/barkod-dukkan.git
Ya da projeyi manuel olarak ZIP formatında indirip açabilirsiniz.

IDE'de Aç:

IntelliJ IDEA, Eclipse veya başka bir Java IDE'sinde projeyi açın.

Gereksinimleri Yükleyin:

Eğer proje Maven kullanıyorsa, gerekli bağımlılıkları yüklemek için:

bash
Kodu kopyala
mvn install
Projeyi Çalıştırın:

Projeyi çalıştırmak için IDE'de App.java dosyasını bulun ve Run seçeneğini seçin.

Kullanım
Uygulama açıldığında marka, model ve parça bilgilerini seçin.

Parça Türü ve Yön Seçimi:

Parça türü olarak Kapı, Kaput, Tampon, Far, Stop gibi seçenekler arasından seçim yapın.
Parça türüne göre uygun yön seçenekleri dinamik olarak karşınıza çıkar (Örneğin, Kapı için Sağ Ön, Sol Arka vb.).
Barkod ve Resim Ekleme:

Resim dosyasını seçin ve barkod bilgisi otomatik olarak oluşturulur.
Parçanın konumunu belirtmek için konum bilgisini girin (Örneğin: "3. raf, sağ üst").
Kaydet:

Parça bilgilerini kaydetmek için "Kaydet" butonuna basın. Barkod ve resim, belirlenen dosya yapısında ilgili klasöre kaydedilir.

Dosya Yapısı
bardkodDukkan/

      ├── markalar/
      │   ├── BMW/
      │   │   ├── X5/
      │   │   │   ├── Kapı/
      │   │   │   │   ├── sağ_on/
      │   │   │   │   ├── sol_on/
      │   │   │   │   └── sağ_arka/
      │   │   └── Tampon/
      │   └── Mercedes/
      │       ├── C Serisi/
      │       └── E Serisi/
      ├── src/
      │   ├── main/
      │   │   ├── java/
      │   │   │   └── AdaYildirim/
      │   │   │       └── App.java
      ├── README.md
      └── pom.xml

markalar/: Bu klasör, her bir markanın altındaki modelleri ve parçaları içerir. Parçalar, ilgili barkod ve resim dosyalarını içerir.
src/: Uygulamanın ana kaynak kodlarını içerir.
pom.xml: Maven bağımlılıklarını ve proje yapılandırmalarını içerir.
Örnek Kaydedilen Klasör Yapısı
Eğer bir Mercedes marka "C Serisi" aracına ait "Kapı" parçası sağ ön seçilirse, dosya yapısı şu şekilde olacaktır:


markalar/Mercedes/C Serisi/Kapı/Sağ Ön/
      ├── 0000000000001.jpg   # Parçanın resmi
      ├── 0000000000001.txt   # Barkod bilgileri

Yapılacaklar
Daha fazla araç parçası ekleme
Barkod tarama ve parça sorgulama özelliği ekleme
Veritabanı entegrasyonu ile parça yönetimi
Katkıda Bulunma
Katkıda bulunmak için:

Projeyi forklayın
Yeni bir branch oluşturun (git checkout -b özellik-branch)
Değişikliklerinizi commitleyin (git commit -am 'Yeni özellik ekle')
Branch'i push edin (git push origin özellik-branch)
Bir Pull Request oluşturun



//Pojeye Yeni marka eklemek için

    import java.io.File;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;

    public class App {
         public static void main(String[] args) {
            // Volvo modellerini oluştur ve listeye ekle
        List<Model> volvoModelleri = new ArrayList<>();
        volvoModelleri.add(new Model(1L, "C30"));
        volvoModelleri.add(new Model(2L, "C70"));
        volvoModelleri.add(new Model(3L, "S40"));
        volvoModelleri.add(new Model(4L, "S60"));
        volvoModelleri.add(new Model(5L, "S70"));
        volvoModelleri.add(new Model(6L, "S80"));
        volvoModelleri.add(new Model(7L, "S90"));
        volvoModelleri.add(new Model(8L, "V40"));
        volvoModelleri.add(new Model(9L, "V40 Cross Country"));
        volvoModelleri.add(new Model(10L, "V50"));
        volvoModelleri.add(new Model(11L, "V60"));
        volvoModelleri.add(new Model(12L, "V60 Cross Country"));
        volvoModelleri.add(new Model(13L, "V70"));
        volvoModelleri.add(new Model(14L, "V90"));
        volvoModelleri.add(new Model(15L, "V90 Cross Country"));
        volvoModelleri.add(new Model(16L, "440"));
        volvoModelleri.add(new Model(17L, "460"));
        volvoModelleri.add(new Model(18L, "740"));
        volvoModelleri.add(new Model(19L, "850"));
        volvoModelleri.add(new Model(20L, "940"));
        volvoModelleri.add(new Model(21L, "960"));
        volvoModelleri.add(new Model(22L, "C40"));
        volvoModelleri.add(new Model(23L, "XC40"));
        volvoModelleri.add(new Model(24L, "XC60"));
        volvoModelleri.add(new Model(25L, "XC70"));
        volvoModelleri.add(new Model(26L, "XC90"));

        // Volvo markasını oluştur
        Marka volvo = new Marka(1L, "Volvo", volvoModelleri);

        // Parça isimlerini bir diziye ekleyelim
        String[] parcaIsimleri = {"Kapı", "Kaput", "Far", "Stop", "Çamurluk", "Tampon"};

        // Volvo markasının modellerini for döngüsü ile kaydet
        try {
            File anaKlasor = new File("markalar/Volvo");

            // Eğer Volvo klasörü yoksa oluştur
            if (!anaKlasor.exists()) {
                anaKlasor.mkdirs();
            }

            // For döngüsü ile tüm Volvo modellerini kaydet
            for (Model model : volvo.getModeller()) {
                File modelKlasoru = new File(anaKlasor, model.getModelIsmi());
                if (!modelKlasoru.exists()) {
                    modelKlasoru.mkdirs();  // Eğer model klasörü yoksa oluştur
                    System.out.println(model.getModelIsmi() + " modeli için klasör oluşturuldu.");
                }

                // Her modelin içine parça dosyaları ekle
                for (String parca : parcaIsimleri) {
                    File parcaKlasoru = new File(modelKlasoru, parca);
                    if (!parcaKlasoru.exists()) {
                        parcaKlasoru.mkdirs();  // Eğer parça klasörü yoksa oluştur
                        System.out.println(model.getModelIsmi() + " modeli için " + parca + " klasörü oluşturuldu.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}