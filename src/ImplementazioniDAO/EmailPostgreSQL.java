package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.EmailDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmailPostgreSQL implements EmailDAO {

    private Connection conn;

    public EmailPostgreSQL() throws SQLException{
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe EmailPostgreSQL");
            e.printStackTrace();
        }
    }
    @Override
    public void inserimentoEmail(int id, String email) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String inserimentoEmail = "INSERT INTO Email(idcontatto, email) VALUES ('"+id+"', '"+email+"');";

        PreparedStatement st = conn.prepareStatement(inserimentoEmail);
        st.executeUpdate();

    }

    @Override
    public void rimozioneEmail(String id, String email) throws SQLException {
        conn = Connessione.getInstance().getConnection();

        String rimozioneEmail = "DELETE FROM Email WHERE idcontatto = '"+id+"' AND email = '"+email+"');";

        PreparedStatement st = conn.prepareStatement(rimozioneEmail);
        st.executeUpdate();
    }
}
