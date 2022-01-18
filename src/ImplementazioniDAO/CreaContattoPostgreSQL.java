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
    public void creaContatto(String nome, String cellulare, String cognome, String fisso) throws SQLException {
       try {
           PreparedStatement inserisciContatto = conn.prepareStatement("Insert into Contatto(cellulare, nome, cognome, fisso) VALUES ('"+cellulare+"','"+nome + "', '"+cognome+"', '"+fisso+"');");

           inserisciContatto.executeQuery();
           conn.close();
       }catch(SQLException e){
           e.printStackTrace();
       }
    }
}
