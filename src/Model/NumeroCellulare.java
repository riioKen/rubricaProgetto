package Model;

public class NumeroCellulare {

    private String numeroCellulare;
    private Long id;

    public NumeroCellulare(String numeroCellulare) {
        this.numeroCellulare = numeroCellulare;
    }

    public NumeroCellulare() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCellulare() {
        return numeroCellulare;
    }

    public void setNumeroCellulare(String numeroCellulare) {
        this.numeroCellulare = numeroCellulare;
    }

    @Override
    public String toString() {
        return "NumeroCellulare{" +
                "numeroCellulare='" + numeroCellulare + '\'' +
                '}';
    }
}
