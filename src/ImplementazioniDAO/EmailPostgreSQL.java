package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.EmailDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public int inserimentoEmail(int id, String email) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String inserimentoEmail = "INSERT INTO Email(idcontatto, email) VALUES ('"+id+"', '"+email+"') RETURNING IDEMAIL;";

        PreparedStatement st = conn.prepareStatement(inserimentoEmail);
        st.execute();
        ResultSet rs = st.getResultSet();
        rs.next();

        return rs.getInt("idEmail");
    }

    @Override
    public boolean controlloDuplicatoEmail(String email) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String rimozioneEmail = "SELECT EXISTS( SELECT email FROM EMAIL WHERE email = '" + email + "');";
        PreparedStatement st = conn.prepareStatement(rimozioneEmail);
        ResultSet rs = st.executeQuery();
            rs.next();

            return rs.getBoolean(1);

    }

    @Override
    public void editEmail(ArrayList<String> listaEemailOld, ArrayList<String> listaTxtEmailNew) {
        for (int i = 0; i < listaEemailOld.size(); i++){
            String updateEmail = "UPDATE Email SET email = '"+listaTxtEmailNew.get(i)+"' WHERE email = '"+listaEemailOld.get(i)+"'";

            try {
                PreparedStatement st = conn.prepareStatement(updateEmail);
                st.execute();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
