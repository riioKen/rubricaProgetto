package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.CreaContattoDAO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class CreaContattoPostgreSQL implements CreaContattoDAO{

    private Connection conn;

    public CreaContattoPostgreSQL(){
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //METODI
    @Override
    public void creaContatto(String nome, String cellulare, String cognome, String fisso, String email, String indirizzo, String foto, String nomeGruppo, ArrayList<JTextField> listaIndirizzi, ArrayList<JTextField> listaEmail) throws SQLException {
        System.out.println(nome+" "+cognome+" "+cellulare+" "+fisso+" "+email+" "+indirizzo+" "+foto);
        int id = 0;
        conn = Connessione.getInstance().getConnection();
        //Inserimento nella tabella Contatto
       try {
           PreparedStatement inserisciContatto = conn.prepareStatement("Insert into Contatto(nome, cognome, foto) VALUES ('"+nome+"', '"+cognome+"', '"+foto+"');");

           inserisciContatto.executeUpdate();

       }catch(SQLException e){
           System.out.println("ECCEZIONE::Riga 36 Classe CreaContattoPostgreSQL");
       }
       //Recupero dell'ID utente
        try {
            String recuperaIDContatto = ("SELECT id FROM Contatto WHERE nome = '"+nome+"' AND cognome = '"+cognome+"' ORDER BY id DESC LIMIT 1");

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(recuperaIDContatto);

            while(rs.next()){
                id = rs.getInt("id");
            }

        }catch(SQLException e){
            System.out.println("ECCEZIONE::Riga 50 Classe CreaContattoPostgreSQL");
        }

        //Inserimento nella tabella EMAIL
        try {
            PreparedStatement inserisciContattoEmail = conn.prepareStatement("Insert into Email (idcontatto, email) VALUES ('"+id+"','"+email+"');");

            inserisciContattoEmail.executeUpdate();

            //Inserimento nella tabella EmailSecondario
            inserimentoEmailSecondarie(listaEmail, id);
        }catch(SQLException e){
            System.out.println("ECCEZIONE::Riga 62 Classe CreaContattoPostgreSQL");
        }

        try {
            //Inserimento nella tabella NumeroCellulare
            PreparedStatement inserisciNumeroCellulare = conn.prepareStatement("INSERT INTO NumeroCellulare (cellulare, idcontatto) VALUES ('"+cellulare+"','"+id+"');");

            inserisciNumeroCellulare.executeUpdate();

        }catch(SQLException e){
            System.out.println("ECCEZIONE::Riga 72 Classe CreaContattoPostgreSQL");
        }

        try {
            //Inserimento nella tabella NumeroFisso
            PreparedStatement inserisciNumeroFisso = conn.prepareStatement("INSERT INTO NumeroFisso (fisso, idcontatto) VALUES ('"+fisso+"','"+id+"');");

            inserisciNumeroFisso.executeUpdate();

        }catch(SQLException e){
            System.out.println("ECCEZIONE::Riga 82 Classe CreaContattoPostgreSQL");
        }
        //Inserimento nella tabella IndirizzoPrincipale
        try {
            splittaIndirizzo(indirizzo, id);

        }catch(SQLException e){
            System.out.println("ECCEZIONE::Riga 89 Classe CreaContattoPostgreSQL");
        }

        //Inserimento nella tabella IndirizzoSecondario
        try {
            splittaIndirizzoSecondario(listaIndirizzi, id);

        }catch(SQLException e){
            System.out.println("ECCEZIONE::Riga 97 Classe CreaContattoPostgreSQL");
        }
        //Inserimento nella tabella Partecipazione
        try {
            PreparedStatement inserisciPersonaInGruppo = conn.prepareStatement("INSERT INTO Partecipazione (idcontatto, nome) VALUES ('"+id+"','"+nomeGruppo+"');");
            inserisciPersonaInGruppo.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("ECCEZIONE::Riga 105 Classe CreaContattoPostgreSQL");
        }
        conn.close();
    }

    public void inserimentoEmailSecondarie(ArrayList<JTextField> listaEmail, int id) throws  SQLException {
        String email;
        try {
            if (listaEmail != null) {
                for (int i = 0; i < listaEmail.size(); i++) {
                    email = listaEmail.get(i).getText();
                    if (!email.isBlank()) {
                        PreparedStatement inserisciContattoEmail = conn.prepareStatement("Insert into EmailSecondario (email, idcontatto) VALUES ('" + email + "','" + id + "');");
                        inserisciContattoEmail.executeUpdate();
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 117 Classe CreaContattoPostgreSQL");
        }
    }

    public void splittaIndirizzoSecondario(ArrayList<JTextField> listaIndirizzo, int id) throws SQLException {
        String via, civico, cap, citta, nazione;

        try {
            if (listaIndirizzo != null) {
                for (int i = 0; i < listaIndirizzo.size(); i++) {
                    if (listaIndirizzo.get(i).getText() != null || !listaIndirizzo.get(i).getText().isBlank()) {
                        System.out.println("stampa di quante volte entra nella i " + i);

                        via = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[0];
                        civico = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[1];
                        cap = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[2];
                        citta = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[3];
                        nazione = listaIndirizzo.get(i).getText().split("\\s*,\\s*")[4];
                        System.out.println(via + " " + citta);
                        if (!via.isBlank() && !civico.isBlank() && !cap.isBlank() && !citta.isBlank() && !nazione.isBlank()) {
                            PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("Insert into IndirizzoSecondario(idcontatto, via, civico, cap, citta, nazione) VALUES ('" + id + "','" + via + "', '" + civico + "', '" + cap + "', '" + citta + "','" + nazione + "');");
                            inserisciContattoIndirizzo.executeUpdate();
                        }
                    }
                }
            }
        }
        catch (SQLException e){
            System.out.println("ECCEZIONE::Riga 145 Classe CreaContattoPostgreSQL");
        }
    }

    public void splittaIndirizzo(String indirizzo, int id) throws SQLException {
        try {
            String via, civico, cap, citta, nazione;
            String[] divisione = indirizzo.split("\\s*,\\s*");
            via = divisione[0];
            civico = divisione[1];
            cap = divisione[2];
            citta = divisione[3];
            nazione = divisione[4];

            PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("Insert into IndirizzoPrincipale(idcontatto, via, civico, cap, citta, nazione) VALUES ('" + id + "','" + via + "', '" + civico + "', '" + cap + "', '" + citta + "','" + nazione + "');");

            inserisciContattoIndirizzo.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("ECCEZIONE::Riga 164 Classe CreaContattoPostgreSQL");
        }

    }
}

