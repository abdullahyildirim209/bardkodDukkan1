package AdaYildirim.model;

public class Model {
    private Long id;
    private String modelIsmi;

    public Model(Long id, String modelIsmi) {
        this.id = id;
        this.modelIsmi = modelIsmi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelIsmi() {
        return modelIsmi;
    }

    public void setModelIsmi(String modelIsmi) {
        this.modelIsmi = modelIsmi;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", modelIsmi='" + modelIsmi + '\'' +
                '}';
    }
}
