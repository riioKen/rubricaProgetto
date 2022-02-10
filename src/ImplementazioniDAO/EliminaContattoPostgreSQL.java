package ImplementazioniDAO;

import ConnessioneDB.*;
import DAO.*;
import java.sql.*;

public class EliminaContattoPostgreSQL implements EliminaContattoDAO{

    private Connection conn;

    public EliminaContattoPostgreSQL(){
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminaContatto(String cellulare ) throws SQLException {
        try {
            PreparedStatement queryEliminaContatto = conn.prepareStatement("Delete from Contatto where cellulare = '" + cellulare + "');");

            queryEliminaContatto.executeUpdate();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
