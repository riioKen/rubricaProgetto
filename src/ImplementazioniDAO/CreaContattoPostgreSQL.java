package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.CreaContattoDAO;

import java.sql.*;

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
    public void creaContatto(String nome, String cellulare, String cognome, String fisso, String email, String indirizzo) throws SQLException {
       try {
           PreparedStatement inserisciContatto = conn.prepareStatement("Insert into Contatto(cellulare, nome, cognome, fisso) VALUES ('"+cellulare+"','"+nome+"', '"+cognome+"', '"+fisso+"');");

           inserisciContatto.executeUpdate();

       }catch(SQLException e){
           e.printStackTrace();
       }
        try {
            PreparedStatement inserisciContattoEmail = conn.prepareStatement("Insert into Email (cellulare, email) VALUES ('"+cellulare+"','"+email+"');");

            inserisciContattoEmail.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        try {
            splittaIndirizzo(indirizzo, cellulare);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void splittaIndirizzo(String indirizzo, String cellulare) throws SQLException {
        String via, civico, cap, citta, nazione;
        String[] divisione = indirizzo.split("\\s*,\\s*");
        via = divisione[0];
        civico = divisione[1];
        cap = divisione[2];
        citta = divisione[3];
        nazione = divisione[4];

        PreparedStatement inserisciContattoIndirizzo = conn.prepareStatement("Insert into Indirizzo(cellulare, via, civico, cap, citta, nazione) VALUES ('"+cellulare+"','"+via+"', '"+civico+"', '"+cap+"', '"+citta+"','"+nazione+"');");

        inserisciContattoIndirizzo.executeUpdate();
        conn.close();
    }
}

