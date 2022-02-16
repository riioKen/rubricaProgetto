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
            PreparedStatement queryEliminaContatto = conn.prepareStatement("DELETE FROM Contatto WHERE cellulare = '"+cellulare+"';");
            System.out.println(cellulare);
            queryEliminaContatto.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connessione fallita al database oppure problemi relativi alla cancellazione del contatto");
        }
        conn.close();
    }
}
