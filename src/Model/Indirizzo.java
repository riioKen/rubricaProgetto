package Model;

public class Indirizzo {

    private long id;
    private String via;
    private String civico;
    private String cap;
    private String citta;
    private String nazione;

    private Contatti contatti;

    public Indirizzo(long id, String via, String civico, String cap, String citta, String nazione) {
        this.id = id;
        this.via = via;
        this.civico = civico;
        this.cap = cap;
        this.citta = citta;
        this.nazione = nazione;
        this.contatti = contatti;
    }

    public Indirizzo() {
    }

    public static Indirizzo splitIndirizzo(String indirizzoPrincipale){
        Indirizzo indirizzo = new Indirizzo();
        String via, civico, cap, citta, nazione;
        String[] divisione = indirizzoPrincipale.split("\\s*,\\s*");
        indirizzo.via = divisione[0];
        indirizzo.civico = divisione[1];
        indirizzo.cap = divisione[2];
        indirizzo.citta = divisione[3];
        indirizzo.nazione = divisione[4];
        return indirizzo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public Contatti getContatti() {
        return contatti;
    }

    public void setContatti(Contatti contatti) {
        this.contatti = contatti;
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "via='" + via + '\'' +
                ", civico='" + civico + '\'' +
                ", cap='" + cap + '\'' +
                ", citta='" + citta + '\'' +
                ", nazione='" + nazione + '\'' +
                ", contatti=" + contatti +
                '}';
    }
}
