package ImplementazioniDAO;

import ConnessioneDB.*;
import DAO.*;
import java.sql.*;

public class EliminaContattoPostgreSQL implements EliminaContattoDAO{

    private Connection conn;

    public EliminaContattoPostgreSQL(){

    }

    @Override
    public void eliminaContatto(String cellulare ) throws SQLException {
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement queryEliminaContattoIndirizzo = conn.prepareStatement("DELETE FROM Indirizzo WHERE cellulare = '"+cellulare+"';");
            System.out.println(cellulare);
            queryEliminaContattoIndirizzo.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement queryEliminaContattoEmail = conn.prepareStatement("DELETE FROM Email WHERE cellulare = '"+cellulare+"';");
            System.out.println(cellulare);
            queryEliminaContattoEmail.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement queryEliminaContatto = conn.prepareStatement("DELETE FROM Contatto WHERE cellulare = '"+cellulare+"';");
            System.out.println(cellulare);
            queryEliminaContatto.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        conn.close();
    }
}
