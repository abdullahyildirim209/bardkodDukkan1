package AdaYildirim.model;

public class Parca {
    private Long id;
    private String isim;
    private Marka marka;
    private String kategori; // Yeni özellik: Parça kategorisi (Far, Tampon, Kapı, vb.)
    private double fiyat;
    private String barkod;
    private int stokMiktari;

    public Parca(Long id, String isim, Marka marka, String kategori, double fiyat, String barkod, int stokMiktari) {
        this.id = id;
        this.isim = isim;
        this.marka = marka;
        this.kategori = kategori;
        this.fiyat = fiyat;
        this.barkod = barkod;
        this.stokMiktari = stokMiktari;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public int getStokMiktari() {
        return stokMiktari;
    }

    public void setStokMiktari(int stokMiktari) {
        this.stokMiktari = stokMiktari;
    }

    @Override
    public String toString() {
        return "Parca{" +
                "id=" + id +
                ", isim='" + isim + '\'' +
                ", kategori='" + kategori + '\'' +
                ", marka=" + marka +
                ", fiyat=" + fiyat +
                ", barkod='" + barkod + '\'' +
                ", stokMiktari=" + stokMiktari +
                '}';
    }
}
