package ImplementazioniDAO;

import ConnessioneDB.Connessione;
import DAO.NumeroFissoDAO;
import Model.NumeroFisso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NumeroFissoPostgreSQL implements NumeroFissoDAO {

    private Connection conn;

    public NumeroFissoPostgreSQL() throws SQLException {
        try{
            conn = Connessione.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("ECCEZIONE::Riga 17 Classe NumeroFissoPostgreSQL");
            e.printStackTrace();
        }
    }

    @Override
    public void inserisciFisso(int id, String fisso) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String inserisciFisso = "INSERT INTO NumeroFisso(fisso, idcontatto) VALUES ('"+fisso+"' , '"+id+"')";

        PreparedStatement st = conn.prepareStatement(inserisciFisso);
        st.executeUpdate();
    }

    @Override
    public void modificaFisso(int id, NumeroFisso fisso, String fissoNew) throws SQLException {
        conn = Connessione.getInstance().getConnection();
        String updateFisso = "UPDATE NumeroFisso SET fisso = '"+fissoNew+"' WHERE fisso = '"+fisso.getNumeroFisso()+"'";

        PreparedStatement st = conn.prepareStatement(updateFisso);
        st.executeUpdate();

    }

    @Override
    public NumeroFisso getFisso(int id) throws SQLException {
        String getFisso = ("SELECT fisso FROM NumeroFisso WHERE idContatto = "+id+"");

        PreparedStatement st = conn.prepareStatement(getFisso);
        ResultSet rs = st.executeQuery();
        NumeroFisso fisso = new NumeroFisso();
        while (rs.next()){
            fisso.setNumeroFisso(rs.getString("fisso"));
        }

        System.out.println(fisso);
        return fisso;
    }
}
