package CLASSI;

public class GruppoContatti {
    //ATTRIBUTI
    String nomeGruppo; //Il nome del gruppo di contatti
    int numeroContatti; //Il numero di contatti presenti nel gruppo



    //COSTRUTTORE
    public GruppoContatti(String nomeGruppo, int numeroContatti) {
        this.nomeGruppo = nomeGruppo;
        this.numeroContatti = numeroContatti;
    }



    //GETTER SETTER
    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public int getNumeroContatti() {
        return numeroContatti;
    }

    public void setNumeroContatti(int numeroContatti) {
        this.numeroContatti = numeroContatti;
    }
}
