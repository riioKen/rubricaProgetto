package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.CreaContattoDAO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class CreaContattoPostgreSQL implements CreaContattoDAO{

    private Connection conn;

    public CreaContattoPostgreSQL(){

    }


    //METODI
    @Override
    public void creaContatto(String nome, String cellulare, String cognome, String fisso, String email, String indirizzo, ArrayList<JTextField> listaIndirizzi, ArrayList<JTextField> listaEmail) throws SQLException {
        int id = 0;
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Inserimento nella tabella Contatto
       try {
           PreparedStatement inserisciContatto = conn.prepareStatement("Insert into Contatto(nome, cognome) VALUES ('"+nome+"', '"+cognome+"');");

           inserisciContatto.executeUpdate();

       }catch(SQLException e){
           e.printStackTrace();
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
            System.out.println("Ricerca fallita a causa del ORDER BY");
        }

        //Inserimento nella tabella EMAIL
        try {
            PreparedStatement inserisciContattoEmail = conn.prepareStatement("Insert into Email (idcontatto, email) VALUES ('"+id+"','"+email+"');");

            inserisciContattoEmail.executeUpdate();

            inserimentoEmailSecondarie(listaEmail, id);
        }catch(SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement inserisciNumeroCellulare = conn.prepareStatement("INSERT INTO NumeroCellulare (cellulare, idcontatto) VALUES ('"+cellulare+"','"+id+"');");

            inserisciNumeroCellulare.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

        try {
            PreparedStatement inserisciNumeroFisso = conn.prepareStatement("INSERT INTO NumeroFisso (fisso, idcontatto) VALUES ('"+fisso+"','"+id+"');");

            inserisciNumeroFisso.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        //Inserimento nella tabella IndirizzoPrincipale
        try {
            splittaIndirizzo(indirizzo, id);

        }catch(SQLException e){
            e.printStackTrace();
        }

        try {
            splittaIndirizzoSecondario(listaIndirizzi, id);

        }catch(SQLException e){
            //control.switchJPanelInView(control.getHomepage().getPaneBase());
        }

        conn.close();;
    }

    public void inserimentoEmailSecondarie(ArrayList<JTextField> listaEmail, int id) throws  SQLException{
        String email;
        if(listaEmail != null) {
            for (int i = 0; i < listaEmail.size(); i++) {
                email = listaEmail.get(i).getText();
                if (!email.isBlank()) {
                    PreparedStatement inserisciContattoEmail = conn.prepareStatement("Insert into EmailSecondario (email, idcontatto) VALUES ('" + email + "','" + id + "');");
                    inserisciContattoEmail.executeUpdate();
                }
            }
        }
    }
    public void splittaIndirizzoSecondario(ArrayList<JTextField> listaIndirizzo, int id) throws SQLException {
        String via, civico, cap, citta, nazione;
        String[] divisione;

        if(listaIndirizzo != null) {
            for (int i = 0; i < listaIndirizzo.size(); i++) {
                System.out.println(listaIndirizzo.size());
                divisione = listaIndirizzo.get(i).getText().split("\\s*,\\s*");
                via = divisione[0];
                civico = divisione[1];
                cap = divisione[2];
                citta = divisione[3];
                nazione = divisione[4];

                if (!via.isBlank() && !civico.isBlank() && !cap.isBlank() && !citta.isBlank() && !nazione.isBlank()) {
                    PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("Insert into IndirizzoSecondario(idcontatto, via, civico, cap, citta, nazione) VALUES ('" + id + "','" + via + "', '" + civico + "', '" + cap + "', '" + citta + "','" + nazione + "');");
                    inserisciContattoIndirizzo.executeUpdate();
                }
            }
        }
    }
    public void splittaIndirizzo(String indirizzo, int id) throws SQLException {
        String via, civico, cap, citta, nazione;
        String[] divisione = indirizzo.split("\\s*,\\s*");
        via = divisione[0];
        civico = divisione[1];
        cap = divisione[2];
        citta = divisione[3];
        nazione = divisione[4];

        PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("Insert into IndirizzoPrincipale(idcontatto, via, civico, cap, citta, nazione) VALUES ('"+id+"','"+via+"', '"+civico+"', '"+cap+"', '"+citta+"','"+nazione+"');");

        inserisciContattoIndirizzo.executeUpdate();

    }
}

