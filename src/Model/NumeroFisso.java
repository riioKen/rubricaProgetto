package Model;

public class NumeroFisso {

    private String numeroFisso;


    public NumeroFisso(String numeroFisso) {
        this.numeroFisso = numeroFisso;

    }

    public NumeroFisso() {
    }

    public String getNumeroFisso() {
        return numeroFisso;
    }

    public void setNumeroFisso(String numeroFisso) {
        this.numeroFisso = numeroFisso;
    }

    @Override
    public String toString() {
        return "NumeroFisso{" +
                "numeroFisso='" + numeroFisso + '\'' +
                '}';
    }
}
