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
        conn = Connessione.getInstance().getConnection();
        int id = 0;
        String queryCercaContatto = "SELECT idcontatto FROM NumeroCellulare WHERE cellulare = '"+cellulare+"';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryCercaContatto);

        while(rs.next()){
            id = rs.getInt("idcontatto");
        }
        conn.close();
        eliminazioneEffettivaContatto(id);

    }

    public void eliminazioneEffettivaContatto(int id) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        PreparedStatement eliminaContatto = conn.prepareStatement("DELETE FROM CONTATTO WHERE id = '"+id+"'");
        eliminaContatto.executeUpdate();

        conn.close();
    }
}
