package CLASSI;

public class Contatti {
    //ATTRIBUTI
    private String nome; //Il nome del contatto
    private String cognome; //Il cognome del contatto
    private String foto; //Ragionare su come inserire la foto
    private String email; //L'indirizzo email del contatto
    private String indirizzo; //L'indirizzo di casa del contatto
    private String cellulare; //Il numero di celullare del contatto
    private String fisso; //Il numero di telefono fisso del contatto


    //COSTRUTTORE
    public Contatti(String nome, String cognome, String foto, String email, String indirizzo, String cellulare, String fisso) {
        this.nome = nome;
        this.cognome = cognome;
        this.foto = foto;
        this.email = email;
        this.indirizzo = indirizzo;
        this.cellulare = cellulare;
        this.fisso = fisso;
    }



    //GETTER SETTER
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
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
