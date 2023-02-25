package Model;

public class Contatti {
    //ATTRIBUTI
    private String nome; //Il nome del contatto
    private String cognome; //Il cognome del contatto
    private String foto; //SI INSERISCONO METTENDO DIRETTAMENTE IL PERCORSO DELLA FOTO NEL DATABASE COSI VIENE CARICATA
    private String nomeGruppo;
    private int id;


    //COSTRUTTORE
    public Contatti(String nome, String cognome, int id) {
        this.nome = nome;
        this.cognome = cognome;

        this.id = id;
    }

    public Contatti() {

    }


    //GETTER SETTER


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @Override
    public String toString() {
        return "Contatti{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", foto='" + foto + '\'' +
                ", nomeGruppo='" + nomeGruppo + '\'' +
                ", id=" + id +
                '}';
    }
}
