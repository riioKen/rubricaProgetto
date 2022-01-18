package Classi;

public class Contatti {
    //ATTRIBUTI
    private String nome; //Il nome del contatto
    private String cognome; //Il cognome del contatto
    private String foto; //Ragionare su come inserire la foto
    private String email; //L'indirizzo email del contatto
    private String indirizzo; //L'indirizzo di casa del contatto
    private String cellulare; //Il numero di celullare del contatto
    private String fisso; //Il numero di telefono fisso del contatto
    private String whatsapp;



    //COSTRUTTORE
    public Contatti(String nome, String cognome, String cellulare, String fisso) {
        this.nome = nome;
        this.cognome = cognome;
        this.cellulare = cellulare;
        this.fisso = fisso;

    }

    public Contatti() {

    }


    //GETTER SETTER

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
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

    public String getCellulare() {
        return cellulare;
    }

    public void setCellulare(String cellulare) {
        this.cellulare = cellulare;
    }

    public String getFisso() {
        return fisso;
    }

    public void setFisso(String fisso) {
        this.fisso = fisso;
    }
}
