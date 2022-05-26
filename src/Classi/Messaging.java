package Classi;


public class Messaging {
    //ATTRIBUTI
    private String nomeFornitore;
    private String nickname;
    private String email;



    //COSTRUTTORE
    public Messaging(String nomeFornitore, String nickname, String email){

        this.nomeFornitore = nomeFornitore;
        this.nickname = nickname;
        this.email = email;
    }



    //GETTER SETTER
    public String getNomeFornitore() {
        return nomeFornitore;
    }

    public void setNomeFornitore(String nomeFornitore) {
        this.nomeFornitore = nomeFornitore;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
