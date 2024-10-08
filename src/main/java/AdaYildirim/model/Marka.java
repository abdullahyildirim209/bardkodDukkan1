package AdaYildirim.model;

import java.util.List;

public class Marka {
    private Long id;
    private String isim;
    private List<Model> modeller;


    // Constructor
    public Marka(Long id, String isim, List<Model> modeller) {
        this.id = id;
        this.isim = isim;
        this.modeller = modeller;
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

    public List<Model> getModeller() {
        return modeller;
    }

    public void setModeller(List<Model> modeller) {
        this.modeller = modeller;
    }

    @Override
    public String toString() {
        return "Marka{" +
                "id=" + id +
                ", isim='" + isim + '\'' +
                ", modeller=" + modeller +
                '}';
    }
}
