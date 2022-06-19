package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.CercaGruppiDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CercaGruppiPostgreSQL implements CercaGruppiDAO {
    private Connection conn;
    ArrayList<String> infoGruppi = new ArrayList<>();

    public CercaGruppiPostgreSQL(){
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //METODI
    @Override
    public ArrayList<String> cercaGruppi() throws SQLException {
        conn = Connessione.getInstance().getConnection();

        String queryCercaGruppi = ("SELECT * FROM Gruppo");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(queryCercaGruppi);
        String nome;
        String membri;
        infoGruppi.clear();
        while (rs.next()){
            nome = rs.getString("nome");
            infoGruppi.add(nome);
        }

        return infoGruppi;
    }
}
