package AdaYildirim.model;

public class Marka {
    private Long id;
    private String isim;

    // Constructor
    public Marka(Long id, String isim) {
        this.id = id;
        this.isim = isim;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return "Marka{" +
                "id=" + id +
                ", isim='" + isim + '\'' +
                '}';
    }
}
